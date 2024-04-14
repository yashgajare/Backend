package com.backend.Controller;

import com.backend.Models.ProjectTable;
import com.backend.Services.ProjectServices;
import com.backend.UtilityClasses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    @Autowired
    ProjectServices projectServices;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllProjects(){
        ApiResponse<List<ProjectTable>> res = projectServices.getAllProjects();
        return new ResponseEntity<>(res, HttpStatusCode.valueOf(res.getStatusCode()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addProject(@RequestBody ProjectTable project){
        ApiResponse<ProjectTable> res = projectServices.addProject(project);
        return new ResponseEntity<>(res,HttpStatusCode.valueOf(res.getStatusCode()));
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateProject(@RequestBody ProjectTable project){
        ApiResponse<ProjectTable> res = projectServices.updateProject(project);
        return new ResponseEntity<>(res,HttpStatusCode.valueOf(res.getStatusCode()));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> deleteProject(@PathVariable Integer id){
        ApiResponse<ProjectTable> res = projectServices.deleteProject(id);
        return new ResponseEntity<>(res,HttpStatusCode.valueOf(res.getStatusCode()));
    }
}
