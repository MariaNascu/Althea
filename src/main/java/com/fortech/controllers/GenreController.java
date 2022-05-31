package com.fortech.controllers;

import com.fortech.entity.Genre;
import com.fortech.service.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("myapi")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }


    @PostMapping("/createGenre")
    public void createGenre(@RequestParam(name = "genreName") String genreName) {
        genreService.createGenre(genreName);

    }

    @GetMapping("/getAllGenre")
    public List<Genre> findAll() {
        return genreService.findAll();
    }

}
