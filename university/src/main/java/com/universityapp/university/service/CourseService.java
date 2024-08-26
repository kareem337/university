package com.universityapp.university.service;

import com.universityapp.university.dto.CourseDTO;
import com.universityapp.university.entity.Course;
import com.universityapp.university.mapper.CourseMapper;
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

    @Autowired
    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
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
        System.out.println(course.getAuthor_id());
        courseRepository.save(course);
        return courseDTO;
    }

    public CourseDTO updateCourse(int id, CourseDTO updatedCourseDTO) {
        // Fetch the existing course from the repository
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        // Update only the fields that are present in the updatedCourseDTO
        if (updatedCourseDTO.getName() != null) {
            course.setName(updatedCourseDTO.getName());
        }
        if (updatedCourseDTO.getDescription() != null) {
            course.setDescription(updatedCourseDTO.getDescription());
        }
        if (updatedCourseDTO.getCredit() != 0) { // Assuming credit cannot be 0 as valid value
            course.setCredit(updatedCourseDTO.getCredit());
        }

        // Save the updated course back to the repository
        Course updatedCourse = courseRepository.save(course);

        // Convert the updated course to DTO and return
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
