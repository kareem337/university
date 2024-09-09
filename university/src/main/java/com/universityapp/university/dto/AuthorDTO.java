package com.universityapp.university.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

public class AuthorDTO {
    @Size(max = 100, message = "Author name cannot exceed 100 characters")
    private String name;
    @Size(max = 500, message = "Email cannot exceed 500 characters")
    private String email;
    private String birthdate;
    @JsonIgnore
    private Set<CourseDTO> courses;

    public Set<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseDTO> courses) {
        this.courses = courses;
    }


    public AuthorDTO() {
        this.courses = new HashSet<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }


}
