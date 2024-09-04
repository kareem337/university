package com.universityapp.university.repository;

import com.universityapp.university.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByEmail(String email);

    @Query("SELECT COUNT(ac) > 0 FROM AuthorCourse  ac WHERE ac.author.author_id = :authorId")
    boolean existsByAuthorId(@Param("authorId") int authorId);
}
