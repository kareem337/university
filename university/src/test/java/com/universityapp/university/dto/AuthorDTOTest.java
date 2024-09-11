package com.universityapp.university.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AuthorDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        // Initialize the validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidAuthorDTO() {

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("Jane Doe");
        authorDTO.setEmail("jane.doe@example.com");
        authorDTO.setBirthdate("1990-05-15");

        Set<ConstraintViolation<AuthorDTO>> violations = validator.validate(authorDTO);

        assertTrue(violations.isEmpty(), "There should be no validation errors");
    }

    @Test
    void testAuthorDTOWithEmptyName() {
        // Given
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName(""); // Invalid name
        authorDTO.setEmail("jane.doe@example.com");
        authorDTO.setBirthdate("1990-05-15");

        // When
        Set<ConstraintViolation<AuthorDTO>> violations = validator.validate(authorDTO);

        // Then
        assertFalse(violations.isEmpty(), "There should be validation errors");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Author name cannot be empty")));
    }

    @Test
    void testAuthorDTOWithLongName() {
        // Given
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("A".repeat(101)); // Invalid name length
        authorDTO.setEmail("jane.doe@example.com");
        authorDTO.setBirthdate("1990-05-15");

        // When
        Set<ConstraintViolation<AuthorDTO>> violations = validator.validate(authorDTO);

        // Then
        assertFalse(violations.isEmpty(), "There should be validation errors");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Author name cannot exceed 100 characters")));
    }

    @Test
    void testAuthorDTOWithEmptyEmail() {
        // Given
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("Jane Doe");
        authorDTO.setEmail(""); // Invalid email
        authorDTO.setBirthdate("1990-05-15");

        // When
        Set<ConstraintViolation<AuthorDTO>> violations = validator.validate(authorDTO);

        // Then
        assertFalse(violations.isEmpty(), "There should be validation errors");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("email cannot be empty")));
    }

    @Test
    void testAuthorDTOWithLongEmail() {
        // Given
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("Jane Doe");
        authorDTO.setEmail("A".repeat(501)); // Invalid email length
        authorDTO.setBirthdate("1990-05-15");

        // When
        Set<ConstraintViolation<AuthorDTO>> violations = validator.validate(authorDTO);

        // Then
        assertFalse(violations.isEmpty(), "There should be validation errors");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Email cannot exceed 500 characters")));
    }

    @Test
    void testAuthorDTOWithEmptyBirthdate() {
        // Given
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("Jane Doe");
        authorDTO.setEmail("jane.doe@example.com");
        authorDTO.setBirthdate(""); // Invalid birthdate

        // When
        Set<ConstraintViolation<AuthorDTO>> violations = validator.validate(authorDTO);

        // Then
        assertFalse(violations.isEmpty(), "There should be validation errors");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Birthdate cannot be empty")));
    }


}
