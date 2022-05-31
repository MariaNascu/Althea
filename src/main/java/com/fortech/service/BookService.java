package com.fortech.service;

import com.fortech.dtos.BookFilterInput;
import com.fortech.dtos.BookInput;
import com.fortech.dtos.BookResponse;
import com.fortech.entity.Book;

import java.text.ParseException;
import java.util.List;

public interface BookService {

    public Book create(BookInput bookInput);

    public List<BookResponse> findAll(BookFilterInput bookFilterInput) throws ParseException;

    public Book findByTitle (String title);

    public Book findById(Integer bookId);

    public Book update(Integer bookId, Book book);

    public void delete (Integer bookId);



}
