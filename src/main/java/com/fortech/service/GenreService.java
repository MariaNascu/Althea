package com.fortech.service;

import com.fortech.entity.Genre;
import java.util.List;

public interface GenreService {

     void createGenre(String genreName);

     public List<Genre> findAll();
}
