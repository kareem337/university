package com.universityapp.university.mapper;

import com.universityapp.university.dto.AuthorDTO;
import com.universityapp.university.dto.CourseDTO;
import com.universityapp.university.entity.Author;
import com.universityapp.university.entity.Course;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-04T03:04:32+0300",
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
        courseDTO.setAuthors( authorSetToAuthorDTOSet( course.getAuthors() ) );

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
        course.setAuthors( authorDTOSetToAuthorSet( courseDto.getAuthors() ) );

        return course;
    }

    protected AuthorDTO authorToAuthorDTO(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorDTO authorDTO = new AuthorDTO();

        authorDTO.setName( author.getName() );
        authorDTO.setEmail( author.getEmail() );
        authorDTO.setBirthdate( author.getBirthdate() );

        return authorDTO;
    }

    protected Set<AuthorDTO> authorSetToAuthorDTOSet(Set<Author> set) {
        if ( set == null ) {
            return null;
        }

        Set<AuthorDTO> set1 = new LinkedHashSet<AuthorDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Author author : set ) {
            set1.add( authorToAuthorDTO( author ) );
        }

        return set1;
    }

    protected Author authorDTOToAuthor(AuthorDTO authorDTO) {
        if ( authorDTO == null ) {
            return null;
        }

        Author author = new Author();

        author.setName( authorDTO.getName() );
        author.setEmail( authorDTO.getEmail() );
        author.setBirthdate( authorDTO.getBirthdate() );

        return author;
    }

    protected Set<Author> authorDTOSetToAuthorSet(Set<AuthorDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Author> set1 = new LinkedHashSet<Author>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AuthorDTO authorDTO : set ) {
            set1.add( authorDTOToAuthor( authorDTO ) );
        }

        return set1;
    }
}
