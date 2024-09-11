package com.universityapp.university.service;

import com.universityapp.university.dto.AuthorDTO;
import com.universityapp.university.entity.Author;
import com.universityapp.university.exception.AuthorNotFoundException;
import com.universityapp.university.mapper.AuthorMapper;
import com.universityapp.university.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorService authorService;

    private Author author;
    private AuthorDTO authorDTO;

    @BeforeEach
    void setUp() {
        author = new Author();
        author.setAuthor_id(1);
        author.setName("kareem");
        author.setEmail("kareem@gmail.com");
        author.setBirthdate("9/7/2000");

        authorDTO = new AuthorDTO();

        authorDTO.setName("kareem");
        authorDTO.setEmail("jkareem@gmail.com");
        authorDTO.setBirthdate("9/7/2000");
    }

    @Test
    void testGetAllAuthors() {
        when(authorRepository.findAll()).thenReturn(Arrays.asList(author));
        when(authorMapper.authorToAuthorDTO(any(Author.class))).thenReturn(authorDTO);

        List<AuthorDTO> authorDTOs = authorService.getAllAuthors();

        assertNotNull(authorDTOs);
        assertEquals(1, authorDTOs.size());
        verify(authorRepository, times(1)).findAll();
        verify(authorMapper, times(1)).authorToAuthorDTO(any(Author.class));
    }

    @Test
    void testGetAuthorById_Found() {
        when(authorRepository.findById(anyInt())).thenReturn(Optional.of(author));
        when(authorMapper.authorToAuthorDTO(any(Author.class))).thenReturn(authorDTO);

        AuthorDTO foundAuthorDTO = authorService.getAuthorById(1);

        assertNotNull(foundAuthorDTO);
        assertEquals("kareem", foundAuthorDTO.getName());
        verify(authorRepository, times(1)).findById(1);
    }

    @Test
    void testGetAuthorById_NotFound() {
        when(authorRepository.findById(anyInt())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authorService.getAuthorById(1);
        });

        assertEquals("Author not found with id: 1", exception.getMessage());
        verify(authorRepository, times(1)).findById(1);
    }

    @Test
    void testCreateAuthor() {
        when(authorMapper.authorDTOToAuthor(any(AuthorDTO.class))).thenReturn(author);
        when(authorRepository.save(any(Author.class))).thenReturn(author);
        when(authorMapper.authorToAuthorDTO(any(Author.class))).thenReturn(authorDTO);

        AuthorDTO createdAuthorDTO = authorService.createAuthor(authorDTO);

        assertNotNull(createdAuthorDTO);
        assertEquals(authorDTO.getName(), createdAuthorDTO.getName());
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    void testUpdateAuthor() {
        when(authorRepository.findById(anyInt())).thenReturn(Optional.of(author));
        when(authorRepository.save(any(Author.class))).thenReturn(author);
        when(authorMapper.authorToAuthorDTO(any(Author.class))).thenReturn(authorDTO);

        AuthorDTO updatedAuthorDTO = authorService.updateAuthor(1, authorDTO);

        assertNotNull(updatedAuthorDTO);
        assertEquals(authorDTO.getName(), updatedAuthorDTO.getName());
        verify(authorRepository, times(1)).findById(1);
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    void testDeleteAuthor() {
        when(authorRepository.existsById(anyInt())).thenReturn(true);
        doNothing().when(authorRepository).deleteById(anyInt());

        assertDoesNotThrow(() -> authorService.deleteAuthor(1));

        verify(authorRepository, times(1)).existsById(1);
        verify(authorRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteAuthor_NotFound() {
        when(authorRepository.existsById(anyInt())).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authorService.deleteAuthor(1);
        });

        assertEquals("Author not found with id: 1", exception.getMessage());
        verify(authorRepository, times(1)).existsById(1);
        verify(authorRepository, never()).deleteById(anyInt());
    }

    @Test
    void testGetAuthorByEmail_Found() {
        when(authorRepository.findByEmail(anyString())).thenReturn(Optional.of(author));
        when(authorMapper.authorToAuthorDTO(any(Author.class))).thenReturn(authorDTO);

        AuthorDTO foundAuthorDTO = authorService.getAuthorByEmail("kareem@gmail.com");

        assertNotNull(foundAuthorDTO);
        assertEquals("kareem", foundAuthorDTO.getName());
        verify(authorRepository, times(1)).findByEmail("kareem@gmail.com");
    }

    @Test
    void testGetAuthorByEmail_NotFound() {
        when(authorRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(AuthorNotFoundException.class, () -> {
            authorService.getAuthorByEmail("omar@gmail.com");
        });

        assertEquals("Author not found with email: omar@gmail.com", exception.getMessage());
    }


}
