package com.universityapp.university.recommender;

import java.util.Arrays;
import java.util.List;

public class SecondCourseRecommender implements CourseRecommender {
    @Override
    public List<String> recommendCourses() {
        return Arrays.asList("Recent Course 1", "Recent Course 2", "Recent Course 3");
    }
}
