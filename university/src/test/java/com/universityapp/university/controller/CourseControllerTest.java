package com.universityapp.university.controller;

import com.universityapp.university.dto.CourseDTO;
import com.universityapp.university.exception.CourseNotFoundException;
import com.universityapp.university.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    private CourseDTO courseDTO;

    @BeforeEach
    void setUp() {
        courseDTO = new CourseDTO();
        courseDTO.setName("Math");
        courseDTO.setDescription("Math by Kareem");
        courseDTO.setCredit(2);
    }

    @Test
    void testCreateCourse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"courseId\":1,\"name\":\"Math\",\"description\":\"Math by Kareem\",\"credit\":2,\"authorId\":1}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    void testGetAllCourses() throws Exception {
        when(courseService.getAllCourses()).thenReturn(Arrays.asList(courseDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/courses"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Math"));
    }

    @Test
    void testGetCourseById() throws Exception {
        when(courseService.getCourseById(anyInt())).thenReturn(courseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/courses/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Math"));
    }

    @Test
    void testGetCourseByIdNotFound() throws Exception {

        when(courseService.getCourseById(anyInt())).thenThrow(new CourseNotFoundException("Course not found"));


        mockMvc.perform(MockMvcRequestBuilders.get("/courses/{id}", 999))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    void testUpdateCourse() throws Exception {

        CourseDTO updatedCourseDTO = new CourseDTO();

        updatedCourseDTO.setName("Math Updated");
        updatedCourseDTO.setDescription("Updated Description");
        updatedCourseDTO.setCredit(3);


        when(courseService.updateCourse(anyInt(), any(CourseDTO.class))).thenReturn(updatedCourseDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/courses/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"courseId\":1,\"name\":\"Math Updated\",\"description\":\"Updated Description\",\"credit\":3,\"authorId\":1}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Math Updated"));
    }


    @Test
    void testDeleteCourse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/courses/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testGetCoursesWithPagination() throws Exception {
        Page<CourseDTO> coursePage = new PageImpl<>(Arrays.asList(courseDTO));
        when(courseService.getCoursesWithPagination(anyInt(), anyInt())).thenReturn(coursePage);

        mockMvc.perform(MockMvcRequestBuilders.get("/courses/paginated?page=1&size=10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value("Math"));
    }
}
