package com.backend.models;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class ContactTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "Name cannot be blank!")
	private String name;
	
	private String organizationName;
	
	@NotBlank(message = "Email cannot be blank!")
	@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message="Invalid Email")
	private String email;
	
	@Column(length = 10)
	@NotBlank(message = "Contact Number cannot be blank!")
	private String contactNo;
	
	@Column(length = 1000)
	private String description;
}
