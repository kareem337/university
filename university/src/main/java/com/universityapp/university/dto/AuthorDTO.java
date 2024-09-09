package com.universityapp.university.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class AuthorDTO {

    @Size(max = 100, message = "Author name cannot exceed 100 characters")
    private String name;

    @Size(max = 500, message = "Email cannot exceed 500 characters")
    private String email;

    private String birthdate;

    @JsonIgnore
    private Set<CourseDTO> courses = new HashSet<>();
}
