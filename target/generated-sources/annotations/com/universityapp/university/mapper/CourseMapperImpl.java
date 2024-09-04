package com.universityapp.university.mapper;

import com.universityapp.university.dto.CourseDTO;
import com.universityapp.university.entity.Course;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-04T13:22:02+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public CourseDTO courseToCourseDTO(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseDTO courseDTO = new CourseDTO();

        courseDTO.setName( course.getName() );
        courseDTO.setDescription( course.getDescription() );
        courseDTO.setCredit( course.getCredit() );

        return courseDTO;
    }

    @Override
    public Course dtoToCourse(CourseDTO courseDto) {
        if ( courseDto == null ) {
            return null;
        }

        Course course = new Course();

        course.setName( courseDto.getName() );
        course.setDescription( courseDto.getDescription() );
        course.setCredit( courseDto.getCredit() );

        return course;
    }
}
