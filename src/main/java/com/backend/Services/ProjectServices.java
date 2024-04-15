package com.backend.Services;

import com.backend.models.ProjectTable;
import com.backend.Repository.ProjectRepository;
import com.backend.UtilityClasses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServices {

    @Autowired
    ProjectRepository projectRepository;

    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/images/project";
    
    public ApiResponse<List<ProjectTable>> getAllProjects() {
        return new ApiResponse<List<ProjectTable>>(200,"success","List of all projects : ",projectRepository.findAll());
    }

    public ApiResponse<ProjectTable> addProject(ProjectTable project, MultipartFile file) throws IOException {
        if(project.getDate()==null || project.getDate().isAfter(LocalDate.now())) return new ApiResponse<ProjectTable>(400,"Failed","Date not valid");
        if(project.getProjectType()==null || project.getProjectType().isEmpty()) return new ApiResponse<ProjectTable>(400,"Failed","Project Type must be provided");
        if(project.getName()==null || project.getName().isEmpty()) return new ApiResponse<ProjectTable>(400,"Failed","Please provide project name");
        if(project.getDescription()==null || project.getDescription().isEmpty()) return new ApiResponse<ProjectTable>(400,"Failed","Description cannot be empty");
        if(project.getName().length()<3) return new ApiResponse<ProjectTable>(400,"Failed","Project name is too small");
//        if(project.getImageUrl()==null || project.getImageUrl().isEmpty()) return new ApiResponse<ProjectTable>(400,"Failed","Image invalid");
        
        String originalFilename = file.getOriginalFilename();
		System.out.println("original Name: "+originalFilename);
		Path pth = Paths.get(uploadDirectory, originalFilename);
		System.out.println("Path: "+pth);
		Files.write(pth, file.getBytes());
		project.setImageUrl(originalFilename);
        
        projectRepository.save(project);
        return new ApiResponse<ProjectTable>(200,"success","Project added successfully");
    }


    public ApiResponse<ProjectTable> deleteProject(Integer id) {
        Optional<ProjectTable> pt = projectRepository.findById(id);
        if(!pt.isPresent()) return new ApiResponse<ProjectTable>(400,"Failed","Project doesn't exist");
        projectRepository.deleteById(id);
        
        System.out.println(pt.get().getImageUrl());
		
		if (pt.get().getImageUrl() != null && !pt.get().getImageUrl().isEmpty()) {
          
			try {
				Path imagePath = Paths.get(uploadDirectory, pt.get().getImageUrl());
                Files.deleteIfExists(imagePath);
			} catch (IOException e) {
				e.printStackTrace();
				return new ApiResponse<>(500, "Internal Server Error", "Server Error");
			}  
        }
        
        return new ApiResponse<>(200,"success","Project Deleted successfully");
    }

    public ApiResponse<ProjectTable> updateProject(ProjectTable project, Integer id, MultipartFile file) {
        
    	Optional<ProjectTable> pt = projectRepository.findById(id);
        System.out.println(project.getId());
        if(!pt.isPresent()) return new ApiResponse<>(400,"Failed","Project doesn't exists");
        
        try {
        	if(project.getDate()!=null && !project.getDate().isAfter(LocalDate.now())) pt.get().setDate(project.getDate());
            if(project.getProjectType()!=null && !project.getProjectType().isEmpty()) pt.get().setProjectType(project.getProjectType());
            if(project.getName()!=null && !project.getName().isEmpty()) pt.get().setName(project.getName());
            if(project.getDescription()!=null && !project.getDescription().isEmpty()) pt.get().setDescription(project.getDescription());
            
            if(!file.isEmpty()) {
				
				//delete old pic
				Path imagePath = Paths.get(uploadDirectory, pt.get().getImageUrl());
                Files.deleteIfExists(imagePath);
                
				//save new pic
                String originalFilename = file.getOriginalFilename();
        		System.out.println("original Name: "+originalFilename);
        		Path pth = Paths.get(uploadDirectory, originalFilename);
        		System.out.println("Path: "+pth);
        		Files.write(pth, file.getBytes());
        		pt.get().setImageUrl(originalFilename);
			}
            
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResponse<>(500, "Internal Server Error", "Server Error");
		}
        
        projectRepository.save(pt.get());
        return new ApiResponse<>(200,"success","Project edited successfully");
    }
}
