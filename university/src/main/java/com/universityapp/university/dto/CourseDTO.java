package com.universityapp.university.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class CourseDTO {

    @Min(1)
    private int courseId;

    @NotEmpty(message = "Course name cannot be empty")
    @Size(max = 100, message = "Course name cannot exceed 100 characters")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @Min(value = 1, message = "Credit must be at least 1")
    private int credit;

    @NotNull(message = "Author ID cannot be null")
    @Min(value = 1, message = "Author ID must be a positive integer")
    private int authorId;

    private Set<AuthorDTO> authors;

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }


    public CourseDTO() {}



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


}
