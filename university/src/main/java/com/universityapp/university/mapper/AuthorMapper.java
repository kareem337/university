package com.universityapp.university.mapper;

import com.universityapp.university.dto.AuthorDTO;
import com.universityapp.university.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mapping(target = "courses", ignore = true)
    AuthorDTO authorToAuthorDTO(Author author);
    @Mapping(target = "courses", ignore = true)
    Author authorDTOToAuthor(AuthorDTO authorDTO);
}
