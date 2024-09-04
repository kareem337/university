package com.universityapp.university.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.universityapp.university.dto.AuthorDTO;
import com.universityapp.university.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private AuthorDTO authorDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
        objectMapper = new ObjectMapper();

        authorDTO = new AuthorDTO();

        authorDTO.setName("kareem");
        authorDTO.setEmail("kareem@gmail.com");
        authorDTO.setBirthdate("9/7/2000");
    }

    @Test
    void testGetAllAuthors() throws Exception {
        when(authorService.getAllAuthors()).thenReturn(Arrays.asList(authorDTO));

        mockMvc.perform(get("/authors")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(authorDTO.getName()))
                .andExpect(jsonPath("$[0].email").value(authorDTO.getEmail()))
                .andExpect(jsonPath("$[0].birthdate").value(authorDTO.getBirthdate()));

        verify(authorService, times(1)).getAllAuthors();
    }

    @Test
    void testGetAuthorById() throws Exception {
        when(authorService.getAuthorById(anyInt())).thenReturn(authorDTO);

        mockMvc.perform(get("/authors/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(authorDTO.getName()))
                .andExpect(jsonPath("$.email").value(authorDTO.getEmail()))
                .andExpect(jsonPath("$.birthdate").value(authorDTO.getBirthdate()));

        verify(authorService, times(1)).getAuthorById(1);
    }

    @Test
    void testCreateAuthor() throws Exception {
        when(authorService.createAuthor(any(AuthorDTO.class))).thenReturn(authorDTO);

        mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authorDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(authorDTO.getName()))
                .andExpect(jsonPath("$.email").value(authorDTO.getEmail()))
                .andExpect(jsonPath("$.birthdate").value(authorDTO.getBirthdate()));

        verify(authorService, times(1)).createAuthor(any(AuthorDTO.class));
    }

    @Test
    void testUpdateAuthor() throws Exception {
        when(authorService.updateAuthor(anyInt(), any(AuthorDTO.class))).thenReturn(authorDTO);

        mockMvc.perform(put("/authors/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authorDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(authorDTO.getName()))
                .andExpect(jsonPath("$.email").value(authorDTO.getEmail()))
                .andExpect(jsonPath("$.birthdate").value(authorDTO.getBirthdate()));

        verify(authorService, times(1)).updateAuthor(anyInt(), any(AuthorDTO.class));
    }

    @Test
    void testDeleteAuthor() throws Exception {
        doNothing().when(authorService).deleteAuthor(anyInt());

        mockMvc.perform(delete("/authors/{id}", 1))
                .andExpect(status().isNoContent());

        verify(authorService, times(1)).deleteAuthor(1);
    }

    @Test
    void testGetAuthorByEmail() throws Exception {
        when(authorService.getAuthorByEmail(anyString())).thenReturn(authorDTO);

        mockMvc.perform(get("/authors/email")
                        .param("email", "kareem@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(authorDTO.getName()))
                .andExpect(jsonPath("$.email").value(authorDTO.getEmail()))
                .andExpect(jsonPath("$.birthdate").value(authorDTO.getBirthdate()));

        verify(authorService, times(1)).getAuthorByEmail("kareem@gmail.com");
    }
}
