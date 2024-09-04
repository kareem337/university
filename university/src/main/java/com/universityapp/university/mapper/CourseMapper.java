package com.universityapp.university.mapper;

import com.universityapp.university.dto.CourseDTO;
import com.universityapp.university.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "authors", ignore = true) //to ignore the looping between course and author sets because the many to many relation
    CourseDTO courseToCourseDTO(Course course);
    @Mapping(target = "authors", ignore = true)
    Course dtoToCourse(CourseDTO courseDto);
}
