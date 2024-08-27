package com.universityapp.university.mapper;

import com.universityapp.university.dto.AuthorDTO;
import com.universityapp.university.entity.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorMapperTest {

    private AuthorMapper authorMapper;

    @BeforeEach
    void setUp() {
        authorMapper = Mappers.getMapper(AuthorMapper.class);
    }

    @Test
    void testAuthorToAuthorDTO() {
        // Given
        Author author = new Author();
        author.setAuthor_id(1);
        author.setName("kareem");
        author.setEmail("kareem@gmail.com");
        author.setBirthdate("9/7/2000");

        // When
        AuthorDTO authorDTO = authorMapper.authorToAuthorDTO(author);

        // Then
        assertEquals(1, authorDTO.getAuthorId());
        assertEquals("kareem", authorDTO.getName());
        assertEquals("kareem@gmail.com", authorDTO.getEmail());
        assertEquals("9/7/2000", authorDTO.getBirthdate());
    }

    @Test
    void testAuthorDTOToAuthor() {
        // Given
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorId(1);
        authorDTO.setName("kareem");
        authorDTO.setEmail("kareem@gmail.com");
        authorDTO.setBirthdate("9/7/2000");

        // When
        Author author = authorMapper.authorDTOToAuthor(authorDTO);

        // Then
        assertEquals(1, author.getAuthor_id());
        assertEquals("kareem", author.getName());
        assertEquals("kareem@gmail.com", author.getEmail());
        assertEquals("9/7/2000", author.getBirthdate());
    }
}
