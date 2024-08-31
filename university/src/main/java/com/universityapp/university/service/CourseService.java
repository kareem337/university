package com.universityapp.university.service;

import com.universityapp.university.dto.CourseDTO;
import com.universityapp.university.entity.Course;
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
    private final CourseMapper courseMapper;
    private final AuthorRepository authorRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper, AuthorRepository authorRepository) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.authorRepository = authorRepository;
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
        if ( !authorRepository.existsById(course.getAuthor_id())) {
            throw new RuntimeException("Author not found with id: " + course.getAuthor_id());
        }
        System.out.println(course.getAuthor_id());
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
        if (mappedCourse.getAuthor_id() != 0) {
            if ( !authorRepository.existsById(mappedCourse.getAuthor_id())) {
                throw new RuntimeException("Author not found with id: " + mappedCourse.getAuthor_id());
            }
            else
            {existingCourse.setAuthor_id(mappedCourse.getAuthor_id());}
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
}
