package com.universityapp.university.service;

import com.universityapp.university.dto.AuthorDTO;
import com.universityapp.university.dto.CourseDTO;
import com.universityapp.university.entity.Author;
import com.universityapp.university.entity.Course;
import com.universityapp.university.mapper.AuthorMapper;
import com.universityapp.university.mapper.CourseMapper;
import com.universityapp.university.repository.AuthorRepository;
import com.universityapp.university.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
                .orElse(null);
    }

    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = courseMapper.dtoToCourse(courseDTO);
        courseRepository.save(course);
        return courseDTO;
    }

    public CourseDTO updateCourse(int id, CourseDTO updatedCourseDTO) {

        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        Course mappedCourse = courseMapper.dtoToCourse(updatedCourseDTO);

        if (mappedCourse.getName() != null) {
            existingCourse.setName(mappedCourse.getName());
        }
        if (mappedCourse.getDescription() != null) {
            existingCourse.setDescription(mappedCourse.getDescription());
        }
        if (mappedCourse.getCredit() != 0) {
            existingCourse.setCredit(mappedCourse.getCredit());
        }

        Course updatedCourse = courseRepository.save(existingCourse);
        return courseMapper.courseToCourseDTO(updatedCourse);
    }




    public void deleteCourse(int id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        } else {
            throw new RuntimeException("Course not found with id: " + id);
        }
    }

    public Page<CourseDTO> getCoursesWithPagination(int page, int pageSize) {
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



