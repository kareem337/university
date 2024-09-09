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
public class AuthorDTO {

    @NotEmpty(message = "Author name cannot be empty")
    @Size(max = 100, message = "Author name cannot exceed 100 characters")
    private String name;

    @NotEmpty(message = "email cannot be empty")
    @Size(max = 500, message = "Email cannot exceed 500 characters")
    private String email;

    @NotEmpty(message = "Birthdate cannot be empty")
    private String birthdate;

    @JsonIgnore
    private Set<CourseDTO> courses = new HashSet<>();
}
