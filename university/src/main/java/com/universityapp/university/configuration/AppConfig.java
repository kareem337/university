package com.universityapp.university.configuration;

import com.universityapp.university.recommender.CourseRecommender;
import com.universityapp.university.recommender.FirstCourseRecommender;
import com.universityapp.university.recommender.SecondCourseRecommender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.beans.factory.annotation.Qualifier;

@Configuration
public class AppConfig {

    @Bean
    @Primary
    public CourseRecommender firstCourseRecommender() {
        return new FirstCourseRecommender();
    }

    @Bean
    @Qualifier("second")
    public CourseRecommender secondCourseRecommender() {
        return new SecondCourseRecommender();
    }
}

