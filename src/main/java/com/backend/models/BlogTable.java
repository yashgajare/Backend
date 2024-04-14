package com.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class BlogTable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "Upload Id must!")
	private String uploadId;
	
	@NotBlank(message = "Role cannot be Blank!")
	private String role;
	
	@NotBlank(message = "Write about yourself!")
	private String about;

	public BlogTable(){}

	public BlogTable(Integer id, String uploadId, String role, String about) {
		this.id = id;
		this.uploadId = uploadId;
		this.role = role;
		this.about = about;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUploadId() {
		return uploadId;
	}

	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
}
