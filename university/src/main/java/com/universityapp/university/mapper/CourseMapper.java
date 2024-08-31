package com.universityapp.university.mapper;

import com.universityapp.university.dto.CourseDTO;
import com.universityapp.university.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = AuthorMapper.class)
public interface CourseMapper {

    @Mapping(target = "courseId", source = "course_id")
    @Mapping(target = "authorId", source = "author_id")
    CourseDTO courseToCourseDTO(Course course);


    @Mapping(target = "author_id", source = "authorId")
    @Mapping(target = "course_id", source = "courseId")
    Course dtoToCourse(CourseDTO courseDto);
}
