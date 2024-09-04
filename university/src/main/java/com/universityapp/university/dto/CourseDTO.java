package com.universityapp.university.dto;

import java.util.HashSet;
import java.util.Set;

public class CourseDTO {


    private String name;
    private String description;
    private int credit;
    private Set<AuthorDTO> authors;



    public CourseDTO() {
        this.authors = new HashSet<>();
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

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Set<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorDTO> authors) {
        this.authors = authors;
    }
}
