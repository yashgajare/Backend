package com.backend.Services;

import com.backend.models.ProjectTable;
import com.backend.Repository.ProjectRepository;
import com.backend.UtilityClasses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServices {

    @Autowired
    ProjectRepository projectRepository;

    public ApiResponse<List<ProjectTable>> getAllProjects() {
        return new ApiResponse<List<ProjectTable>>(200,"success","List of all projects : ",projectRepository.findAll());
    }

    public ApiResponse<ProjectTable> addProject(ProjectTable project) {
        if(project.getDate()==null || project.getDate().isAfter(LocalDate.now())) return new ApiResponse<ProjectTable>(400,"Failed","Date not valid");
        if(project.getProjectType()==null || project.getProjectType().isEmpty()) return new ApiResponse<ProjectTable>(400,"Failed","Project Type must be provided");
        if(project.getName()==null || project.getName().isEmpty()) return new ApiResponse<ProjectTable>(400,"Failed","Please provid project name");
        if(project.getDescription()==null || project.getDescription().isEmpty()) return new ApiResponse<ProjectTable>(400,"Failed","Description cannot be empty");
        if(project.getImageUrl()==null || project.getImageUrl().isEmpty()) return new ApiResponse<ProjectTable>(400,"Failed","Image invalid");
        if(project.getName().length()<3) return new ApiResponse<ProjectTable>(400,"Failed","Project name is too small");
        projectRepository.save(project);
        return new ApiResponse<ProjectTable>(200,"success","Project added successfully");
    }


    public ApiResponse<ProjectTable> deleteProject(Integer id) {
        Optional<ProjectTable> pt = projectRepository.findById(id);
        if(!pt.isPresent()) return new ApiResponse<ProjectTable>(400,"Failed","Project doesn't exist");
        projectRepository.deleteById(id);
        return new ApiResponse<>(200,"success","Project Deleted successfully");
    }

    public ApiResponse<ProjectTable> updateProject(ProjectTable project) {
        Optional<ProjectTable> pt = projectRepository.findById(project.getId());
        if(!pt.isPresent()) return new ApiResponse<>(400,"Failed","Project doesn't exits");
        if(project.getDate()!=null && !project.getDate().isAfter(LocalDate.now())) pt.get().setDate(project.getDate());
        if(project.getProjectType()!=null && !project.getProjectType().isEmpty()) pt.get().setProjectType(project.getProjectType());
        if(project.getName()!=null && !project.getName().isEmpty()) pt.get().setName(project.getName());
        if(project.getDescription()!=null && !project.getDescription().isEmpty()) pt.get().setDescription(project.getDescription());
        if(project.getImageUrl()!=null && !project.getImageUrl().isEmpty()) pt.get().setImageUrl(project.getImageUrl());
        projectRepository.save(pt.get());
        return new ApiResponse<>(200,"success","Project edited successfully");
    }
}
