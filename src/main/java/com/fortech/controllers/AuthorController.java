package com.fortech.controllers;

import com.fortech.entity.Author;
import com.fortech.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("myapi")
public class AuthorController {

    private final AuthorService authorService;


    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/createAuthor")
    public void createAuthor(@RequestParam(name = "authorFirstName") String authorFirstName,
                             @RequestParam(name = "authorLastName") String authorLastName) {
        authorService.createAuthor(authorFirstName, authorLastName);

    }

    @GetMapping("/getAllAuthor")
    public List<Author> findAll() {
        return authorService.findAll();


    }
}
