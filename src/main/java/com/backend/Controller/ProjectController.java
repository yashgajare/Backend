	package com.backend.Controller;

import com.backend.models.ProjectTable;
import com.backend.Services.ProjectServices;
import com.backend.UtilityClasses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public ResponseEntity<ApiResponse> addProject(@ModelAttribute ProjectTable project, @RequestParam("image") MultipartFile file) throws IOException{
        ApiResponse<ProjectTable> res = projectServices.addProject(project, file);
        return new ResponseEntity<>(res,HttpStatusCode.valueOf(res.getStatusCode()));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> updateProject(@ModelAttribute ProjectTable project, @PathVariable("id") Integer id, @RequestParam("image") MultipartFile file){
        ApiResponse<ProjectTable> res = projectServices.updateProject(project, id, file);
        return new ResponseEntity<>(res,HttpStatusCode.valueOf(res.getStatusCode()));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> deleteProject(@PathVariable Integer id){
        ApiResponse<ProjectTable> res = projectServices.deleteProject(id);
        return new ResponseEntity<>(res,HttpStatusCode.valueOf(res.getStatusCode()));
    }
}
