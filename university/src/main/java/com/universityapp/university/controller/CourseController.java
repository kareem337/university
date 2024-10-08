package com.universityapp.university.controller;

import com.universityapp.university.dto.CourseDTO;
import com.universityapp.university.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable int id) {
        CourseDTO course = courseService.getCourseById(id);
        return course != null ? ResponseEntity.ok(course) : ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO course) {
        CourseDTO createdCourse = courseService.createCourse(course);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable int id, @RequestBody CourseDTO updatedCourse) {
        CourseDTO course = courseService.updateCourse(id, updatedCourse);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<CourseDTO>> getCoursesWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<CourseDTO> coursePage = courseService.getCoursesWithPagination(page, size);
        return ResponseEntity.ok(coursePage);
    }

    @PostMapping("/{courseId}/assign/{authorId}")
    public ResponseEntity<Void> assignCourseToAuthor(@PathVariable int courseId, @PathVariable int authorId) {
        courseService.assignCourseToAuthor(courseId, authorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
