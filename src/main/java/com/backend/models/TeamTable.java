package com.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class TeamTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "Name cannot be blank!")
	private String name;
	
	@NotBlank(message = "Designation cannot be blank!")
	private String designation;
	
	@NotBlank(message = "Write about yourself")
	private String about;
	
	private String ImageUrl;
}
