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
}
