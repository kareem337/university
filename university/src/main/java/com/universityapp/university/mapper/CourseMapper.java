package com.universityapp.university.mapper;

import com.universityapp.university.dto.CourseDTO;
import com.universityapp.university.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = AuthorMapper.class)
public interface CourseMapper {

    @Mapping(target = "courseId", source = "course_id")
    @Mapping(target = "authorId", source = "author_id")
    @Mapping(target = "authors", ignore = true) //to ignore the looping between course and author sets because the many to many relation
    CourseDTO courseToCourseDTO(Course course);


    @Mapping(target = "author_id", source = "authorId")
    Course dtoToCourse(CourseDTO courseDto);
}
