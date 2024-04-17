package com.backend.Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import com.backend.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.UtilityClasses.ApiResponse;
import com.backend.models.ReviewTable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ReviewServices {
    @Autowired
    private ReviewRepository reviewRepository;

    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/images/project";
    
    @GetMapping
    public ApiResponse<List<ReviewTable>>getAllReview(){
        List<ReviewTable> reviews = reviewRepository.findAll();
        return new ApiResponse<List<ReviewTable>>(200, "Success", "List of all reviews: ", reviews);
    }

    @PostMapping
    public ApiResponse<ReviewTable> addReview(ReviewTable review, MultipartFile file) throws IOException{
        if(review.getName()==null || review.getName().isEmpty()) return new ApiResponse<>(400, "failed", "Please provide your reviewer name!");
//        if(review.getPhoto()==null || review.getPhoto().isEmpty()) return new ApiResponse<>(400, "failed", "Please provide your reviewer's photo!");
        if(review.getRole()==null || review.getRole().isEmpty()) return new ApiResponse<>(400, "failed", "Please provide your role!");
        if(review.getAbout()==null || review.getAbout().isEmpty()) return new ApiResponse<>(400, "failed", "Please write about yourself!");

        String originalFilename = file.getOriginalFilename();
		System.out.println("original Name: "+originalFilename);
		Path pth = Paths.get(uploadDirectory, originalFilename);
		System.out.println("Path: "+pth);
		Files.write(pth, file.getBytes());
		review.setPhoto(originalFilename);
        
        reviewRepository.save(review);
        return new ApiResponse<>(200, "success", "review Added Successfully");
    }

    @DeleteMapping
    public ApiResponse<ReviewTable> deleteReview(Integer id){
        Optional<ReviewTable> review = reviewRepository.findById(id);
        if(!review.isPresent()) return new ApiResponse<ReviewTable>(400, "failed", "review Doesn't Exist");

        reviewRepository.deleteById(id);
        
        if (review.get().getPhoto() != null && !review.get().getPhoto().isEmpty()) {
            
			try {
				Path imagePath = Paths.get(uploadDirectory, review.get().getPhoto());
                Files.deleteIfExists(imagePath);
			} catch (IOException e) {
				e.printStackTrace();
				return new ApiResponse<>(500, "Internal Server Error", "Server Error");
			}  
        }
        
        return new ApiResponse<ReviewTable>(200, "success", "review Deleted Successfully!");
    }

    @PutMapping
    public ApiResponse<ReviewTable> updateReview(ReviewTable review, MultipartFile file){
        Optional<ReviewTable> bg = reviewRepository.findById(review.getId());
        if(!bg.isPresent()) return new ApiResponse<ReviewTable>(400, "failed", "review Doesn't Exist");

      try {
    	  if(review.getName()!=null && !review.getName().isEmpty()) bg.get().setName(review.getName());
//        if(review.getPhoto()!=null && !review.getPhoto().isEmpty()) bg.get().setPhoto(review.getPhoto());
        if(review.getRole()!=null && !review.getRole().isEmpty()) bg.get().setRole(review.getRole());
        if(review.getAbout()!=null && !review.getAbout().isEmpty()) bg.get().setAbout(review.getAbout());

        if(!file.isEmpty()) {
			
			//delete old pic
			Path imagePath = Paths.get(uploadDirectory, bg.get().getPhoto());
            Files.deleteIfExists(imagePath);
            
			//save new pic
            String originalFilename = file.getOriginalFilename();
    		System.out.println("original Name: "+originalFilename);
    		Path pth = Paths.get(uploadDirectory, originalFilename);
    		System.out.println("Path: "+pth);
    		Files.write(pth, file.getBytes());
    		bg.get().setPhoto(originalFilename);
		}
        
	} catch (Exception e) {
		e.printStackTrace();
		return new ApiResponse<>(500, "Internal Server Error", "Server Error");
	}
      
        reviewRepository.save(bg.get());
        return new ApiResponse<ReviewTable>(200, "success", "review Updated Successfully");
    }
}
