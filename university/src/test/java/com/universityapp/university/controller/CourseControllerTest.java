package com.universityapp.university.controller;

import com.universityapp.university.dto.CourseDTO;
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
        courseDTO.setCourseId(1);
        courseDTO.setName("Math");
        courseDTO.setDescription("Math by Kareem");
        courseDTO.setCredit(2);
        courseDTO.setAuthorId(1);
    }

    @Test
    void testCreateCourse() throws Exception {
        when(courseService.createCourse(any(CourseDTO.class))).thenReturn(courseDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"courseId\":1,\"name\":\"Math\",\"description\":\"Math by Kareem\",\"credit\":2,\"authorId\":1}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Math"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Math by Kareem"));
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
    void testUpdateCourse() throws Exception {
        // Arrange
        CourseDTO updatedCourseDTO = new CourseDTO();
        updatedCourseDTO.setCourseId(1);
        updatedCourseDTO.setName("Math Updated");
        updatedCourseDTO.setDescription("Updated Description");
        updatedCourseDTO.setCredit(3);
        updatedCourseDTO.setAuthorId(1);

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

        mockMvc.perform(MockMvcRequestBuilders.get("/courses/paginated?page=0&size=10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value("Math"));
    }
}
