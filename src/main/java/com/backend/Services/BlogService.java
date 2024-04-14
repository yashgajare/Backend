package com.backend.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.Repository.BlogRepository;
import com.backend.UtilityClasses.ApiResponse;
import com.backend.models.BlogTable;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;
	
	public ApiResponse<List<BlogTable>>getAllBlog(){
		List<BlogTable> blogs = blogRepository.findAll();
		return new ApiResponse<List<BlogTable>>(200, "Success", "List of all blogs: ", blogs);
	}
	
	public ApiResponse<BlogTable> addBlog(BlogTable blog){
		if(blog.getUploadId()==null || blog.getUploadId().isEmpty()) return new ApiResponse<>(400, "failed", "Please provide your id!");
		if(blog.getRole()==null || blog.getRole().isEmpty()) return new ApiResponse<>(400, "failed", "Please provide your role!");
		if(blog.getAbout()==null || blog.getAbout().isEmpty()) return new ApiResponse<>(400, "failed", "Please write about yourself!");
		
		blogRepository.save(blog);
		return new ApiResponse<>(200, "success", "Blog Added Successfully");
	}
	
	public ApiResponse<BlogTable> deleteBlog(Integer id){
		Optional<BlogTable> blog = blogRepository.findById(id);
		if(!blog.isPresent()) return new ApiResponse<BlogTable>(400, "failed", "Blog Doesn't Exist");
		
		blogRepository.deleteById(id);
		return new ApiResponse<BlogTable>(200, "success", "Blog Deleted Successfully!");
	}
	
	public ApiResponse<BlogTable> updateBlog(BlogTable blog){
		Optional<BlogTable> bg = blogRepository.findById(blog.getId());
		if(!bg.isPresent()) return new ApiResponse<BlogTable>(400, "failed", "Blog Doesn't Exist");
		
		if(blog.getUploadId()!=null || !blog.getUploadId().isEmpty()) bg.get().setUploadId(blog.getUploadId());
		if(blog.getRole()!=null || !blog.getRole().isEmpty()) bg.get().setRole(blog.getRole());
		if(blog.getAbout()!=null || !blog.getAbout().isEmpty()) bg.get().setAbout(blog.getAbout());
		
		blogRepository.save(bg.get());
		return new ApiResponse<BlogTable>(200, "success", "Blog Updated Successfully");
	}
	
}
