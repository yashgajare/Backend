package com.backend.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class ProjectTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "Project Name cannot be blank!")
	private String name;
	
	@NotBlank(message = "Description cannot be blank!")
	private String description;
	
	@NotBlank(message = "Date cannot be blank!")
	private Date date;
	
	@NotBlank(message = "Upload the Image!")
	private String imageUrl;
	
	private String projectType;
}
