package com.universityapp.university.entity;

import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int author_id;

    private String name;
    private String email;
    private String birthdate;

    @ManyToMany(mappedBy = "authors")
    private Set<Course> courses = new HashSet<>();

    public Author() {}

    public Author(int author_id, String name, String birthdate, String email) {
        this.author_id = author_id;
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
    }

    // Getters and setters

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}

