package com.universityapp.university.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class CourseDTO {

    @NotEmpty(message = "Course name cannot be empty")
    @Size(max = 100, message = "Course name cannot exceed 100 characters")
    private String name;

    @NotEmpty(message = "Description cannot be empty")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    private int credit;

    @JsonIgnore
    private Set<AuthorDTO> authors = new HashSet<>();
}
