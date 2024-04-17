package com.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class ReviewTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Upload Id must!")
    private String name;

    @NotBlank(message = "Upload Id must!")
    private String photo;

    @NotBlank(message = "Role cannot be Blank!")
    private String role;

    @NotBlank(message = "Write about yourself!")
    private String about;

    public ReviewTable(){}

    public ReviewTable(Integer id, String name, String photo, String role, String about) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.role = role;
        this.about = about;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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