package com.universityapp.university.recommender;

import java.util.Arrays;
import java.util.List;

public class FirstCourseRecommender implements CourseRecommender {
    @Override
    public List<String> recommendCourses() {
        return Arrays.asList("Popular Course 1", "Popular Course 2", "Popular Course 3");
    }
}

