package com.universityapp.university.service;

import com.universityapp.university.dto.AuthorDTO;
import com.universityapp.university.entity.Author;
import com.universityapp.university.exception.AuthorNotFoundException;
import com.universityapp.university.exception.InvalidDataException;
import com.universityapp.university.mapper.AuthorMapper;
import com.universityapp.university.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;


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
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with id: " + id));
        return authorMapper.authorToAuthorDTO(author);
    }

    public AuthorDTO createAuthor(AuthorDTO authorCreateDTO) {
        if (authorCreateDTO.getName() == null || authorCreateDTO.getName().isEmpty()) {
            throw new InvalidDataException("Author name cannot be null or empty.");
        }
        if (authorCreateDTO.getEmail() == null || authorCreateDTO.getEmail().isEmpty()) {
            throw new InvalidDataException("Author email cannot be null or empty.");
        }
        if (authorCreateDTO.getBirthdate() == null || authorCreateDTO.getBirthdate().isEmpty()) {
            throw new InvalidDataException("Birthdate cannot be null or empty.");
        }
        Author author = authorMapper.authorDTOToAuthor(authorCreateDTO);
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.authorToAuthorDTO(savedAuthor);
    }

    public AuthorDTO updateAuthor(int id, AuthorDTO updatedAuthorDTO) {

        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with id: " + id));

        if (updatedAuthorDTO.getName() != null && !updatedAuthorDTO.getName().isEmpty()) {
            author.setName(updatedAuthorDTO.getName());
        }
        if (updatedAuthorDTO.getEmail() != null && !updatedAuthorDTO.getEmail().isEmpty()) {
            author.setEmail(updatedAuthorDTO.getEmail());
        }
        if (updatedAuthorDTO.getBirthdate() != null) {
            author.setBirthdate(updatedAuthorDTO.getBirthdate());
        }

        Author updatedAuthor = authorRepository.save(author);
        return authorMapper.authorToAuthorDTO(updatedAuthor);
    }

    public void deleteAuthor(int id) {
        if (!authorRepository.existsById(id)) {
            throw new AuthorNotFoundException("Author not found with id: " + id);
        }
        if (authorRepository.existsByAuthorId(id)) {
            throw new InvalidDataException("Author cannot be deleted because it is assigned to one or more courses.");
        }
        authorRepository.deleteById(id);
    }

    public AuthorDTO getAuthorByEmail(String email) {
        Author author = authorRepository.findByEmail(email)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with email: " + email));
        return authorMapper.authorToAuthorDTO(author);
    }
}
