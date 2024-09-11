package com.universityapp.university.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CourseDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidCourseDTO() {

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName("Introduction to Programming");
        courseDTO.setDescription("A beginner's course in programming.");
        courseDTO.setCredit(3);


        Set<ConstraintViolation<CourseDTO>> violations = validator.validate(courseDTO);

        // Then
        assertTrue(violations.isEmpty(), "There should be no validation errors");
    }

    @Test
    void testCourseDTOWithEmptyName() {

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName(""); // Invalid name
        courseDTO.setDescription("A beginner's course in programming.");
        courseDTO.setCredit(3);


        Set<ConstraintViolation<CourseDTO>> violations = validator.validate(courseDTO);


        assertFalse(violations.isEmpty(), "There should be validation errors");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Course name cannot be empty")));
    }

    @Test
    void testCourseDTOWithLongDescription() {

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName("Intro Programming");
        courseDTO.setDescription("A".repeat(501)); // Invalid description length
        courseDTO.setCredit(3);


        Set<ConstraintViolation<CourseDTO>> violations = validator.validate(courseDTO);

        // Then
        assertFalse(violations.isEmpty(), "There should be validation errors");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Description cannot exceed 500 characters")));
    }

    @Test
    void testCourseDTOWithValidCredit() {
        // Given
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName("Programming 101");
        courseDTO.setDescription("Basic programming course.");
        courseDTO.setCredit(4); // Valid credit value

        // When
        Set<ConstraintViolation<CourseDTO>> violations = validator.validate(courseDTO);

        // Then
        assertTrue(violations.isEmpty(), "There should be no validation errors for credit");
    }



}
