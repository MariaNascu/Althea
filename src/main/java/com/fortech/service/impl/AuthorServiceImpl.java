package com.fortech.service.impl;

import com.fortech.entity.Author;
import com.fortech.repository.AuthorRepository;
import com.fortech.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void createAuthor(String authorFirstName, String authorLastName) {

        Author newAuthor = new Author();
        newAuthor.setAuthorFirstName(authorFirstName);
        newAuthor.setAuthorLastName(authorLastName);

        authorRepository.save(newAuthor);

    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
