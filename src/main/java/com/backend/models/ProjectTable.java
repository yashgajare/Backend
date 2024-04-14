package com.backend.models;

import java.time.LocalDate;
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
	
	private LocalDate date;
	
	@NotBlank(message = "Upload the Image!")
	private String imageUrl;
	
	private String projectType;

	public ProjectTable(){}

	public ProjectTable(Integer id, String name, String description, LocalDate date, String imageUrl, String projectType) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.date = date;
		this.imageUrl = imageUrl;
		this.projectType = projectType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
}
