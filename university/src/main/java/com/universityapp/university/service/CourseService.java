package com.universityapp.university.service;

import com.universityapp.university.dto.CourseDTO;
import com.universityapp.university.entity.Author;
import com.universityapp.university.entity.Course;
import com.universityapp.university.exception.CourseNotFoundException;
import com.universityapp.university.exception.InvalidDataException;
import com.universityapp.university.exception.InvalidPaginationException;
import com.universityapp.university.mapper.CourseMapper;
import com.universityapp.university.repository.AuthorRepository;
import com.universityapp.university.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    private final AuthorRepository authorRepository;

    private final CourseMapper courseMapper;

    @Autowired
    public CourseService(CourseRepository courseRepository, AuthorRepository authorRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;

        this.authorRepository = authorRepository;

        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(courseMapper::courseToCourseDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO getCourseById(int id) {
        return courseRepository.findById(id)
                .map(courseMapper::courseToCourseDTO)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));
    }

    public CourseDTO createCourse(CourseDTO courseDTO) {
        if (courseDTO.getName() == null || courseDTO.getName().isEmpty()) {
            throw new InvalidDataException("Course name cannot be null or empty.");
        }
        if (courseDTO.getDescription() == null || courseDTO.getDescription().isEmpty()) {
            throw new InvalidDataException("Course description cannot be null or empty.");
        }
        if (courseDTO.getCredit() <= 0) {
            throw new InvalidDataException("Credit must be greater than 0.");
        }
        Course course = courseMapper.dtoToCourse(courseDTO);
        Course savedCourse = courseRepository.save(course);
        return courseMapper.courseToCourseDTO(savedCourse);
    }

    public CourseDTO updateCourse(int id, CourseDTO updatedCourseDTO) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));

        if (updatedCourseDTO.getName() != null) {
            if( updatedCourseDTO.getName().isEmpty())
            {
                throw new InvalidDataException("Course name cannot be empty.");
            }
            else{ course.setName(updatedCourseDTO.getName()); }
        }
        if (updatedCourseDTO.getDescription() != null) {
            if(updatedCourseDTO.getDescription().isEmpty())
            {
                throw new InvalidDataException("Course description cannot be empty.");
            }
           else{ course.setDescription(updatedCourseDTO.getDescription()); }
        }
        if (updatedCourseDTO.getCredit() > 0) {
            course.setCredit(updatedCourseDTO.getCredit());
        } else if (updatedCourseDTO.getCredit() < 0) {
            throw new InvalidDataException("Credit must be greater than 0.");
        }

        Course updatedCourse = courseRepository.save(course);
        return courseMapper.courseToCourseDTO(updatedCourse);
    }

    public void deleteCourse(int id) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }

    public Page<CourseDTO> getCoursesWithPagination(int page, int pageSize) {
        if (page < 0 || pageSize <= 0) {
            throw new InvalidPaginationException("Page and page size must be greater than or equal to 0.");
        }

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Course> coursePage = courseRepository.findAll(pageable);
        return coursePage.map(courseMapper::courseToCourseDTO);
    }


    public void assignCourseToAuthor(int courseId, int authorId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + authorId));

        author.getCourses().add(course);
        course.getAuthors().add(author);

        authorRepository.save(author);
        courseRepository.save(course);
    }
}



