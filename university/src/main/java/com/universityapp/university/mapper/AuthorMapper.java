package com.universityapp.university.mapper;

import com.universityapp.university.dto.AuthorDTO;
import com.universityapp.university.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mapping(target = "authorId", source = "author_id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "birthdate", source = "birthdate")
    AuthorDTO authorToAuthorDTO(Author author);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "birthdate", source = "birthdate")
    Author authorDTOToAuthor(AuthorDTO authorDTO);
}
