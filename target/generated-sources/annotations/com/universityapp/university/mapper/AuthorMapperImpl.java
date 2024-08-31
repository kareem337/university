package com.universityapp.university.mapper;

import com.universityapp.university.dto.AuthorDTO;
import com.universityapp.university.entity.Author;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-31T20:56:03+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public AuthorDTO authorToAuthorDTO(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorDTO authorDTO = new AuthorDTO();

        authorDTO.setAuthorId( author.getAuthor_id() );
        authorDTO.setName( author.getName() );
        authorDTO.setEmail( author.getEmail() );
        authorDTO.setBirthdate( author.getBirthdate() );

        return authorDTO;
    }

    @Override
    public Author authorDTOToAuthor(AuthorDTO authorDTO) {
        if ( authorDTO == null ) {
            return null;
        }

        Author author = new Author();

        author.setAuthor_id( authorDTO.getAuthorId() );
        author.setName( authorDTO.getName() );
        author.setEmail( authorDTO.getEmail() );
        author.setBirthdate( authorDTO.getBirthdate() );

        return author;
    }
}
