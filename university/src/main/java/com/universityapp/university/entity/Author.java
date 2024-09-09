package com.universityapp.university.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "author")
@Data
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int author_id;

    private String name;
    private String email;
    private String birthdate;

    @ManyToMany(mappedBy = "authors")
    private Set<Course> courses = new HashSet<>();
}
