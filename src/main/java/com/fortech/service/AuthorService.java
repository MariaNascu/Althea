package com.fortech.service;

import com.fortech.entity.Author;

import java.util.List;

public interface AuthorService {

    void createAuthor(String authorFirstName, String authorLastName );

    public List<Author> findAll();



}
