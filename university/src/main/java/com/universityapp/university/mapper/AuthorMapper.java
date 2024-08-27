package com.universityapp.university.mapper;

import com.universityapp.university.dto.AuthorDTO;
import com.universityapp.university.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mapping(target = "authorId", source = "author_id")
    AuthorDTO authorToAuthorDTO(Author author);

    Author authorDTOToAuthor(AuthorDTO authorDTO);
}
