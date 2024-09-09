package com.universityapp.university.mapper;

import com.universityapp.university.dto.CourseDTO;
import com.universityapp.university.entity.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CourseMapperTest {

    private CourseMapper courseMapper;

    @BeforeEach
    void setUp() {
        courseMapper = Mappers.getMapper(CourseMapper.class);
    }

    @Test
    void testCourseToCourseDTO() {
        Course course = new Course();
        course.setCourse_id(1);
        course.setName("Math");
        course.setDescription("Math by Kareem");
        course.setCredit(2);

        CourseDTO courseDTO = courseMapper.courseToCourseDTO(course);

        assertEquals(course.getName(), courseDTO.getName());
        assertEquals(course.getDescription(), courseDTO.getDescription());
        assertEquals(course.getCredit(), courseDTO.getCredit());

    }

    @Test
    void testDtoToCourse() {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName("Math");
        courseDTO.setDescription("Math by Kareem");
        courseDTO.setCredit(2);

        Course course = courseMapper.dtoToCourse(courseDTO);

        assertEquals(courseDTO.getName(), course.getName());
        assertEquals(courseDTO.getDescription(), course.getDescription());
        assertEquals(courseDTO.getCredit(), course.getCredit());

    }
}
