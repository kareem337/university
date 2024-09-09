package com.universityapp.university.mapper;

import com.universityapp.university.dto.CourseDTO;
import com.universityapp.university.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "authors", ignore = true)
    CourseDTO courseToCourseDTO(Course course);
    @Mapping(target = "authors", ignore = true)
    Course dtoToCourse(CourseDTO courseDto);
}
