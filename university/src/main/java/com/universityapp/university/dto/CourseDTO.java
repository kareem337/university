package com.universityapp.university.dto;

import java.util.Set;

public class CourseDTO {

    private int courseId;
    private String name;
    private String description;
    private int credit;
    private Set<AuthorDTO> authors; // To hold associated authors
    private int authorId;

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }


    public CourseDTO() {}

    public CourseDTO(int courseId, String name, String description, int credit, int authorId) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.credit = credit;
        this.authorId = authorId;
    }


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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
