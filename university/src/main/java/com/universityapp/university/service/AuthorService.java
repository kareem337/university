package com.universityapp.university.service;

import com.universityapp.university.dto.AuthorDTO;
import com.universityapp.university.entity.Author;
import com.universityapp.university.mapper.AuthorMapper;
import com.universityapp.university.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(authorMapper::authorToAuthorDTO)
                .collect(Collectors.toList());
    }

    public AuthorDTO getAuthorById(int id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
        return authorMapper.authorToAuthorDTO(author);
    }

    public AuthorDTO createAuthor(AuthorDTO authorCreateDTO) {
        Author author = authorMapper.authorDTOToAuthor(authorCreateDTO);
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.authorToAuthorDTO(savedAuthor);
    }

    public AuthorDTO updateAuthor(int id, AuthorDTO updatedAuthorDTO) {
        // Fetch the existing author from the repository
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));

        // Update only the fields that are present in the updatedAuthorDTO
        if (updatedAuthorDTO.getName() != null) {
            author.setName(updatedAuthorDTO.getName());
        }
        if (updatedAuthorDTO.getEmail() != null) {
            author.setEmail(updatedAuthorDTO.getEmail());
        }
        if (updatedAuthorDTO.getBirthdate() != null) {
            author.setBirthdate(updatedAuthorDTO.getBirthdate());
        }

        // Save the updated author back to the repository
        Author updatedAuthor = authorRepository.save(author);

        // Convert the updated author to DTO and return
        return authorMapper.authorToAuthorDTO(updatedAuthor);
    }


    public void deleteAuthor(int id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Author not found with id: " + id);
        }
    }

    public AuthorDTO getAuthorByEmail(String email) {
        Author author = authorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Author not found with email: " + email));
        return authorMapper.authorToAuthorDTO(author);
    }
}
