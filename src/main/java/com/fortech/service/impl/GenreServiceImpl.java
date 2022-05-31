package com.fortech.service.impl;

import com.fortech.entity.Genre;
import com.fortech.repository.GenreRepository;
import com.fortech.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public void createGenre(String genreName) {

        Genre newGenre = new Genre();
        newGenre.setGenreName(genreName);

        genreRepository.save(newGenre);

    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
}
