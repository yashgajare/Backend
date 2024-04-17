package com.backend.Services;

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

@Service
public class ReviewServices {
    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping
    public ApiResponse<List<ReviewTable>>getAllReview(){
        List<ReviewTable> reviews = reviewRepository.findAll();
        return new ApiResponse<List<ReviewTable>>(200, "Success", "List of all reviews: ", reviews);
    }

    @PostMapping
    public ApiResponse<ReviewTable> addReview(ReviewTable review){
        if(review.getName()==null || review.getName().isEmpty()) return new ApiResponse<>(400, "failed", "Please provide your reviewer name!");
        if(review.getPhoto()==null || review.getPhoto().isEmpty()) return new ApiResponse<>(400, "failed", "Please provide your reviewer's photo!");
        if(review.getRole()==null || review.getRole().isEmpty()) return new ApiResponse<>(400, "failed", "Please provide your role!");
        if(review.getAbout()==null || review.getAbout().isEmpty()) return new ApiResponse<>(400, "failed", "Please write about yourself!");

        reviewRepository.save(review);
        return new ApiResponse<>(200, "success", "review Added Successfully");
    }

    @DeleteMapping
    public ApiResponse<ReviewTable> deleteReview(Integer id){
        Optional<ReviewTable> review = reviewRepository.findById(id);
        if(!review.isPresent()) return new ApiResponse<ReviewTable>(400, "failed", "review Doesn't Exist");

        reviewRepository.deleteById(id);
        return new ApiResponse<ReviewTable>(200, "success", "review Deleted Successfully!");
    }

    @PutMapping
    public ApiResponse<ReviewTable> updateReview(ReviewTable review){
        Optional<ReviewTable> bg = reviewRepository.findById(review.getId());
        if(!bg.isPresent()) return new ApiResponse<ReviewTable>(400, "failed", "review Doesn't Exist");

        if(review.getName()!=null && !review.getName().isEmpty()) bg.get().setName(review.getName());
        if(review.getPhoto()!=null && !review.getPhoto().isEmpty()) bg.get().setPhoto(review.getPhoto());
        if(review.getRole()!=null && !review.getRole().isEmpty()) bg.get().setRole(review.getRole());
        if(review.getAbout()!=null && !review.getAbout().isEmpty()) bg.get().setAbout(review.getAbout());

        reviewRepository.save(bg.get());
        return new ApiResponse<ReviewTable>(200, "success", "review Updated Successfully");
    }
}
