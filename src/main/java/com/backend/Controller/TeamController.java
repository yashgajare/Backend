package com.backend.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.Services.TeamService;
import com.backend.UtilityClasses.ApiResponse;
import com.backend.models.TeamTable;

@RestController
@RequestMapping("api/v1/team")
public class TeamController {
	
	@Autowired
	private TeamService teamService;
	
	@GetMapping
	public ResponseEntity<ApiResponse> getAllMembers(){
		ApiResponse<List<TeamTable>> allMembers = teamService.getAllMembers();
		return new ResponseEntity<>(allMembers, HttpStatusCode.valueOf(allMembers.getStatusCode()));
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> addMember(@ModelAttribute TeamTable member, @RequestParam("profileImage") MultipartFile file) throws IOException{
		ApiResponse<TeamTable> mb = teamService.addMember(member, file);
		return new ResponseEntity<>(mb, HttpStatusCode.valueOf(mb.getStatusCode()));
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ApiResponse> deleteMember(@PathVariable Integer id){
		ApiResponse<TeamTable> mb = teamService.deleteMember(id);
		return new ResponseEntity<>(mb, HttpStatusCode.valueOf(mb.getStatusCode()));
	}
	
	@PutMapping
	public ResponseEntity<ApiResponse> updateMember(@ModelAttribute TeamTable member, @RequestParam("profileImage") MultipartFile file){
		ApiResponse<TeamTable> mb = teamService.updateMember(member, file);
		return new ResponseEntity<>(mb, HttpStatusCode.valueOf(mb.getStatusCode()));
	}
}
