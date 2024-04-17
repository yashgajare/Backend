package com.backend.Services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.Repository.TeamRepository;
import com.backend.UtilityClasses.ApiResponse;
import com.backend.models.TeamTable;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;
	
	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/images/team";
	
	public ApiResponse<List<TeamTable>> getAllMembers(){
		return new ApiResponse<List<TeamTable>>(200, "Success", "List of all members: ", teamRepository.findAll());
	}
	
	public ApiResponse<TeamTable> addMember(TeamTable member, MultipartFile file) throws IOException{
		if(member.getName()==null || member.getName().isEmpty()) return new ApiResponse<TeamTable>(400, "failed", "Please provide the member name");
		if(member.getDesignation()==null || member.getDesignation().isEmpty()) return new ApiResponse<TeamTable>(400, "failed", "Please provide designation");
		if(member.getAbout()==null || member.getAbout().isEmpty()) return new ApiResponse<TeamTable>(400, "failed", "Please write about yourself");
//		if(member.getImageUrl()==null || member.getImageUrl().isEmpty()) return new ApiResponse<TeamTable>(400, "failed", "Invalid Image");
		
		System.out.println("Name: "+member.getName());
		
		
		String originalFilename = file.getOriginalFilename();
		System.out.println("original Name: "+originalFilename);
		Path pth = Paths.get(uploadDirectory, originalFilename);
		System.out.println("Path: "+pth);
		Files.write(pth, file.getBytes());
		member.setImageUrl(originalFilename);
		
		teamRepository.save(member);
		return new ApiResponse<TeamTable>(200, "Success", "Member Added Successfully!");
	}
	
	public ApiResponse<TeamTable> deleteMember(Integer id){
		Optional<TeamTable> member = teamRepository.findById(id);
		if(!member.isPresent()) return new ApiResponse<>(400, "failed", "Member Doesn't Exist");
		teamRepository.deleteById(id);
		System.out.println(member.get().getImageUrl());
		
		if (member.get().getImageUrl() != null && !member.get().getImageUrl().isEmpty()) {
          
			try {
				Path imagePath = Paths.get(uploadDirectory, member.get().getImageUrl());
                Files.deleteIfExists(imagePath);
			} catch (IOException e) {
				e.printStackTrace();
				return new ApiResponse<>(500, "Internal Server Error", "Server Error");
			}  
        }
		
		return new ApiResponse<>(200, "Success", "Member Deleted Successfully");
	}
	
	public ApiResponse<TeamTable> updateMember(TeamTable member, MultipartFile file){
		
		Optional<TeamTable> mb = teamRepository.findById(member.getId());

		if(!mb.isPresent()) return new ApiResponse<TeamTable>(400, "failed", "Member Doesn't Exist!");
		
		try {
			if(member.getName()!=null || !member.getName().isEmpty()) mb.get().setName(member.getName());
			if(member.getDesignation()!=null || !member.getDesignation().isEmpty()) mb.get().setDesignation(member.getDesignation());
			if(member.getAbout()!=null || !member.getAbout().isEmpty()) mb.get().setAbout(member.getAbout());

				if(!file.isEmpty()) {
					
					//delete old pic
					Path imagePath = Paths.get(uploadDirectory, mb.get().getImageUrl());
	                Files.deleteIfExists(imagePath);
	                
					//save new pic
	                String originalFilename = file.getOriginalFilename();
	        		System.out.println("original Name: "+originalFilename);
	        		Path pth = Paths.get(uploadDirectory, originalFilename);
	        		System.out.println("Path: "+pth);
	        		Files.write(pth, file.getBytes());
	        		mb.get().setImageUrl(originalFilename);
				}

		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResponse<>(500, "Internal Server Error", "Server Error");
		}
		
		teamRepository.save(mb.get());
		return new ApiResponse<>(200, "Success", "Member Updated Successfully!");
	}
	
}
