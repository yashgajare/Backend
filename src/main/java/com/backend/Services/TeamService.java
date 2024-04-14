package com.backend.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.Repository.TeamRepository;
import com.backend.UtilityClasses.ApiResponse;
import com.backend.models.TeamTable;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;
	
	public ApiResponse<List<TeamTable>> getAllMembers(){
		return new ApiResponse<List<TeamTable>>(200, "Success", "List of all members: ", teamRepository.findAll());
	}
	
	public ApiResponse<TeamTable> addMember(TeamTable member){
		if(member.getName()==null || member.getName().isEmpty()) return new ApiResponse<TeamTable>(400, "failed", "Please provide the member name");
		if(member.getDesignation()==null || member.getDesignation().isEmpty()) return new ApiResponse<TeamTable>(400, "failed", "Please provide designation");
		if(member.getAbout()==null || member.getAbout().isEmpty()) return new ApiResponse<TeamTable>(400, "failed", "Please write about yourself");
		if(member.getImageUrl()==null || member.getImageUrl().isEmpty()) return new ApiResponse<TeamTable>(400, "failed", "Invalid Image");
		
		teamRepository.save(member);
		return new ApiResponse<TeamTable>(200, "Success", "Member Added Successfully!");
	}
	
	public ApiResponse<TeamTable> deleteMember(Integer id){
		Optional<TeamTable> member = teamRepository.findById(id);
		if(!member.isPresent()) return new ApiResponse<>(400, "failed", "Member Doesn't Exist");
		teamRepository.deleteById(id);
		return new ApiResponse<>(200, "Success", "Member Deleted Successfully");
	}
	
	public ApiResponse<TeamTable> updateMember(TeamTable member){
		Optional<TeamTable> mb = teamRepository.findById(member.getId());
		if(!mb.isPresent()) return new ApiResponse<TeamTable>(400, "failed", "Member Doesn't Exist!");
		
		if(member.getName()!=null || !member.getName().isEmpty()) mb.get().setName(member.getName());
		if(member.getDesignation()==null || member.getDesignation().isEmpty()) mb.get().setDesignation(member.getDesignation());
		if(member.getAbout()==null || member.getAbout().isEmpty()) mb.get().setAbout(member.getAbout());
		if(member.getImageUrl()==null || member.getImageUrl().isEmpty()) mb.get().setImageUrl(member.getImageUrl());
		
		teamRepository.save(mb.get());
		return new ApiResponse<>(200, "Success", "Member Updated Successfully!");
	}
	
}
