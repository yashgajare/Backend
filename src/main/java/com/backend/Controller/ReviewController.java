package com.backend.Controller;


import java.io.IOException;
import java.util.List;

import com.backend.Services.ReviewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.backend.UtilityClasses.ApiResponse;
import com.backend.models.ReviewTable;

@RestController
@RequestMapping("api/v1/review")
public class ReviewController {

    @Autowired
    private ReviewServices ReviewService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllReviews(){
        ApiResponse<List<ReviewTable>> allreviews = ReviewService.getAllReview();
        return new ResponseEntity<>(allreviews, HttpStatusCode.valueOf(allreviews.getStatusCode()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addReview(@ModelAttribute ReviewTable review, @RequestParam("image") MultipartFile file) throws IOException{
        ApiResponse<ReviewTable> bg = ReviewService.addReview(review, file);
        return new ResponseEntity<>(bg, HttpStatusCode.valueOf(bg.getStatusCode()));
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<ApiResponse> deleteReview(@PathVariable Integer id){
        ApiResponse<ReviewTable> bg = ReviewService.deleteReview(id);
        return new ResponseEntity<>(bg, HttpStatusCode.valueOf(bg.getStatusCode()));
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateReview(@ModelAttribute ReviewTable review, @RequestParam("image") MultipartFile file){ 
    	
        ApiResponse<ReviewTable> bg = ReviewService.updateReview(review, file);
        return new ResponseEntity<>(bg, HttpStatusCode.valueOf(bg.getStatusCode()));
    }

}
