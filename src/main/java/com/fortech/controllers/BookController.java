package com.fortech.controllers;

import com.fortech.dtos.BookFilterInput;
import com.fortech.dtos.BookInput;
import com.fortech.dtos.BookResponse;
import com.fortech.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("myapi")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/createBook")
    public void createBook(@RequestBody BookInput bookInput) {
        bookService.create(bookInput);
    }

    @GetMapping("/getAllBookAttributes")
    public List<BookResponse> findAll(BookFilterInput bookFilterInput) throws ParseException {
        return bookService.findAll(bookFilterInput);
    }

}
