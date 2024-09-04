package com.universityapp.university.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "author_course")
public class AuthorCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

}
