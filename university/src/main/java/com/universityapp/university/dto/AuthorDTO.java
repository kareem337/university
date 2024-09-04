package com.universityapp.university.dto;

import java.util.HashSet;
import java.util.Set;

public class AuthorDTO {

    private String name;
    private String email;
    private String birthdate;

    public Set<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseDTO> courses) {
        this.courses = courses;
    }

    private Set<CourseDTO> courses;
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
