package com.universityapp.university.mapper;

import com.universityapp.university.dto.AuthorDTO;
import com.universityapp.university.dto.CourseDTO;
import com.universityapp.university.entity.Author;
import com.universityapp.university.entity.Course;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-26T03:15:06+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public CourseDTO courseToCourseDTO(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseDTO courseDTO = new CourseDTO();

        courseDTO.setCourseId( course.getCourse_id() );
        courseDTO.setName( course.getName() );
        courseDTO.setDescription( course.getDescription() );
        courseDTO.setCredit( course.getCredit() );
        courseDTO.setAuthorId( course.getAuthor_id() );

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
        course.setAuthor_id( courseDto.getAuthorId() );
        course.setAuthors( authorDTOSetToAuthorSet( courseDto.getAuthors() ) );

        return course;
    }

    protected Set<Author> authorDTOSetToAuthorSet(Set<AuthorDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Author> set1 = new LinkedHashSet<Author>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AuthorDTO authorDTO : set ) {
            set1.add( authorMapper.authorDTOToAuthor( authorDTO ) );
        }

        return set1;
    }
}
