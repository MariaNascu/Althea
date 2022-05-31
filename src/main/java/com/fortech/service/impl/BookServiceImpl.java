package com.fortech.service.impl;

import com.fortech.dtos.BookFilterInput;
import com.fortech.dtos.BookInput;
import com.fortech.dtos.BookResponse;
import com.fortech.entity.Book;
import com.fortech.repository.*;
import com.fortech.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final SelectAllBooksRepository selectAllBooksRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, PublisherRepository publisherRepository, GenreRepository genreRepository, AuthorRepository authorRepository, SelectAllBooksRepository selectAllBooksRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.selectAllBooksRepository = selectAllBooksRepository;
    }

    public Date convertDate(Object o) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = dateFormat.parse("1.1.2001");

        return date;
    }

    @Override
    public Book create(BookInput bookInput) {

        Book newBook = new Book();
        newBook.setBookTitle(bookInput.getBookTitle());
        newBook.setPublishingYear(bookInput.getPublishingYear());
        newBook.setPublisherId(bookInput.getPublisherId());
        newBook.setAuthorId(bookInput.getAuthorId());
        newBook.setGenreId(bookInput.getGenreId());

        return bookRepository.save(newBook);
    }

    @Override
    public List<BookResponse> findAll(BookFilterInput bookFilterInput) throws ParseException {
        List<BookResponse> bookResponse = new ArrayList<>();
        List<Object[]> resultList = selectAllBooksRepository.selectBookObjects(bookFilterInput.getBookTitle(),bookFilterInput.getAuthorFirstName(),bookFilterInput.getAuthorLastName(),bookFilterInput.getGenreName(),bookFilterInput.getPublisherName());

        for (Object[] result : resultList) {
            BookResponse bookResponseEntity = new BookResponse();
            bookResponseEntity.setBookId(Integer.valueOf(String.valueOf(result[0])));
            bookResponseEntity.setBookTitle(String.valueOf(result[1]));
            bookResponseEntity.setPublishingYear(result[2] == null ? null : convertDate(result[2]));
            bookResponseEntity.setAvailability(String.valueOf(result[3]));
            bookResponseEntity.setPublisherName(String.valueOf(result[4]));
            bookResponseEntity.setGenreName(String.valueOf(result[5]));
            bookResponseEntity.setAuthorFirstName(String.valueOf(result[6]));
            bookResponseEntity.setAuthorLastName(String.valueOf(result[7]));

            bookResponse.add(bookResponseEntity);

        }
        return bookResponse;

    }

    @Override
    public Book findByTitle(String bookTitle) {
        try {
            bookRepository.findByBookTitle(bookTitle);
        } catch (EntityNotFoundException e) {
            System.out.println("Unable to find book " + bookTitle);
        }
        return bookRepository.findByBookTitle(bookTitle);
    }

    @Override
    public Book findById(Integer bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find book " + bookId));
    }

    @Override
    public Book update(Integer bookId, Book book) {

        Book dbBook = findById(bookId);
        dbBook.setBookTitle(book.getBookTitle());
        dbBook.setPublishingYear(book.getPublishingYear());
        dbBook.setAvailability(book.getAvailability());

        return bookRepository.save(dbBook);
    }

    @Override
    public void delete(Integer bookId) {
        bookRepository.deleteById(bookId);

    }
}
