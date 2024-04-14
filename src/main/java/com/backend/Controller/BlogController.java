package com.backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.Services.BlogService;
import com.backend.UtilityClasses.ApiResponse;
import com.backend.models.BlogTable;

@RestController
@RequestMapping("api/v1/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	public ResponseEntity<ApiResponse> getAllBlogs(){
		ApiResponse<List<BlogTable>> allBlogs = blogService.getAllBlog();
		return new ResponseEntity<>(allBlogs, HttpStatusCode.valueOf(allBlogs.getStatusCode()));
	}
	
	public ResponseEntity<ApiResponse> addBlog(@RequestBody BlogTable Blog){
		ApiResponse<BlogTable> bg = blogService.addBlog(Blog);
		return new ResponseEntity<>(bg, HttpStatusCode.valueOf(bg.getStatusCode()));
	}
	
	public ResponseEntity<ApiResponse> deleteBlog(@PathVariable Integer id){
		ApiResponse<BlogTable> bg = blogService.deleteBlog(id);
		return new ResponseEntity<>(bg, HttpStatusCode.valueOf(bg.getStatusCode()));
	}
	
	public ResponseEntity<ApiResponse> updateBlog(@RequestBody BlogTable blog){
		ApiResponse<BlogTable> bg = blogService.updateBlog(blog);
		return new ResponseEntity<>(bg, HttpStatusCode.valueOf(bg.getStatusCode()));
	}
	
}
