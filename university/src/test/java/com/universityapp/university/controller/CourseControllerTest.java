package com.universityapp.university.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.universityapp.university.dto.CourseDTO;
import com.universityapp.university.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private CourseDTO courseDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
        objectMapper = new ObjectMapper();

        courseDTO = new CourseDTO();
        courseDTO.setName("Math");
        courseDTO.setDescription("Math by Kareem");
        courseDTO.setCredit(2);
    }

    @Test
    void testGetAllCourses() throws Exception {
        when(courseService.getAllCourses()).thenReturn(Arrays.asList(courseDTO));

        mockMvc.perform(get("/courses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(courseDTO.getName()))
                .andExpect(jsonPath("$[0].description").value(courseDTO.getDescription()))
                .andExpect(jsonPath("$[0].credit").value(courseDTO.getCredit()));

        verify(courseService, times(1)).getAllCourses();
    }

    @Test
    void testGetCourseById() throws Exception {
        when(courseService.getCourseById(anyInt())).thenReturn(courseDTO);

        mockMvc.perform(get("/courses/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(courseDTO.getName()))
                .andExpect(jsonPath("$.description").value(courseDTO.getDescription()))
                .andExpect(jsonPath("$.credit").value(courseDTO.getCredit()));

        verify(courseService, times(1)).getCourseById(1);
    }

    @Test
    void testCreateCourse() throws Exception {
        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(courseDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateCourse() throws Exception {
        when(courseService.updateCourse(anyInt(), any(CourseDTO.class))).thenReturn(courseDTO);

        mockMvc.perform(put("/courses/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(courseDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(courseDTO.getName()))
                .andExpect(jsonPath("$.description").value(courseDTO.getDescription()))
                .andExpect(jsonPath("$.credit").value(courseDTO.getCredit()));

        verify(courseService, times(1)).updateCourse(anyInt(), any(CourseDTO.class));
    }

    @Test
    void testDeleteCourse() throws Exception {
        doNothing().when(courseService).deleteCourse(anyInt());

        mockMvc.perform(delete("/courses/{id}", 1))
                .andExpect(status().isNoContent());

        verify(courseService, times(1)).deleteCourse(1);
    }

    @Test
    void testGetCoursesWithPagination() throws Exception {


        Page<CourseDTO> coursePage = new PageImpl<>(Collections.singletonList(courseDTO), PageRequest.of(1, 10), 1);
        when(courseService.getCoursesWithPagination(anyInt(), anyInt())).thenReturn(coursePage);

        mockMvc.perform(MockMvcRequestBuilders.get("/courses/paginated?page=1&size=10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value("Math"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].description").value("Math by Kareem"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(10));
    }


}
