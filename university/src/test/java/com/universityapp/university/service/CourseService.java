package com.universityapp.university.service;

import com.universityapp.university.dto.CourseDTO;
import com.universityapp.university.entity.Course;
import com.universityapp.university.mapper.CourseMapper;
import com.universityapp.university.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseMapper courseMapper;

    @InjectMocks
    private CourseService courseService;

    private Course course;
    private CourseDTO courseDTO;

    @BeforeEach
    void setUp() {
        course = new Course();
        course.setCourse_id(1);
        course.setName("Math");
        course.setDescription("Math by kareem");
        course.setCredit(2);
        course.setAuthor_id(1);

        courseDTO = new CourseDTO();
        courseDTO.setCourseId(1);
        courseDTO.setName("Math");
        courseDTO.setDescription("Math by kareem");
        courseDTO.setCredit(2);
        courseDTO.setAuthorId(1);
    }

    @Test
    void testGetAllCourses() {
        when(courseRepository.findAll()).thenReturn(Arrays.asList(course));
        when(courseMapper.courseToCourseDTO(any(Course.class))).thenReturn(courseDTO);

        List<CourseDTO> courseDTOs = courseService.getAllCourses();

        assertNotNull(courseDTOs);
        assertEquals(1, courseDTOs.size());
        verify(courseRepository, times(1)).findAll();
        verify(courseMapper, times(1)).courseToCourseDTO(any(Course.class));
    }

    @Test
    void testGetCourseById_Found() {
        when(courseRepository.findById(anyInt())).thenReturn(Optional.of(course));
        when(courseMapper.courseToCourseDTO(any(Course.class))).thenReturn(courseDTO);

        CourseDTO foundCourseDTO = courseService.getCourseById(1);

        assertNotNull(foundCourseDTO);
        assertEquals("Math", foundCourseDTO.getName());
        verify(courseRepository, times(1)).findById(1);
    }

    @Test
    void testGetCourseById_NotFound() {
        when(courseRepository.findById(anyInt())).thenReturn(Optional.empty());

        CourseDTO foundCourseDTO = courseService.getCourseById(1);

        assertNull(foundCourseDTO);
        verify(courseRepository, times(1)).findById(1);
    }

    @Test
    void testCreateCourse() {
        lenient().when(courseMapper.dtoToCourse(any(CourseDTO.class))).thenReturn(course);
        lenient().when(courseRepository.save(any(Course.class))).thenReturn(course);
        lenient().when(courseMapper.courseToCourseDTO(any(Course.class))).thenReturn(courseDTO);

        CourseDTO savedCourseDTO = courseService.createCourse(courseDTO);

        assertNotNull(savedCourseDTO);
        assertEquals(courseDTO.getName(), savedCourseDTO.getName());
        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    void testUpdateCourse() {
        when(courseRepository.findById(anyInt())).thenReturn(Optional.of(course));
        when(courseRepository.save(any(Course.class))).thenReturn(course);
        when(courseMapper.courseToCourseDTO(any(Course.class))).thenReturn(courseDTO);

        CourseDTO updatedCourseDTO = courseService.updateCourse(1, courseDTO);

        assertNotNull(updatedCourseDTO);
        assertEquals(courseDTO.getName(), updatedCourseDTO.getName());
        verify(courseRepository, times(1)).findById(1);
        verify(courseRepository, times(1)).save(any(Course.class));
    }
    @Test
    void testDeleteCourse() {
        when(courseRepository.existsById(anyInt())).thenReturn(true);
        doNothing().when(courseRepository).deleteById(anyInt());

        assertDoesNotThrow(() -> courseService.deleteCourse(1));

        verify(courseRepository, times(1)).existsById(1);
        verify(courseRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteCourse_NotFound() {
        when(courseRepository.existsById(anyInt())).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            courseService.deleteCourse(1);
        });

        assertEquals("Course not found with id: 1", exception.getMessage());
        verify(courseRepository, times(1)).existsById(1);
        verify(courseRepository, never()).deleteById(anyInt());
    }
    @Test
    void testGetCoursesWithPagination() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Course> coursePage = new PageImpl<>(Arrays.asList(course), pageable, 1);
        when(courseRepository.findAll(any(Pageable.class))).thenReturn(coursePage);
        when(courseMapper.courseToCourseDTO(any(Course.class))).thenReturn(courseDTO);

        Page<CourseDTO> resultPage = courseService.getCoursesWithPagination(0, 2);

        assertNotNull(resultPage);
        assertEquals(1, resultPage.getTotalElements());
        assertEquals(1, resultPage.getContent().size());
        verify(courseRepository, times(1)).findAll(any(Pageable.class));
    }



}
