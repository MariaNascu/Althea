package com.fortech.repository.implementation;

import com.fortech.entity.Author;
import com.fortech.entity.Book;
import com.fortech.entity.Genre;
import com.fortech.entity.Publisher;
import com.fortech.repository.SelectAllBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

import static jdk.internal.dynalink.support.Guards.isNull;

@Repository
public class SelectAllBooksRepositoryImpl implements SelectAllBooksRepository {

    @Autowired
    private EntityManager entityManager;


    @Override
    public List<Object[]> selectBookObjects(String bookTitle, String authorFirstName, String authorLastName, String genreName, String publisherName) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

        Root<Author> authorRoot = criteriaQuery.from(Author.class);
        Root<Book> bookRoot = criteriaQuery.from(Book.class);
        Root<Genre> genreRoot = criteriaQuery.from(Genre.class);
        Root<Publisher> publisherRoot = criteriaQuery.from(Publisher.class);

        ParameterExpression<String> parameterBookTitle = criteriaBuilder.parameter(String.class);
        ParameterExpression<String> parameterAuthorFirstName= criteriaBuilder.parameter(String.class);
        ParameterExpression<String> parameterAuthorLastName= criteriaBuilder.parameter(String.class);
        ParameterExpression<String> parameterGenreName= criteriaBuilder.parameter(String.class);
        ParameterExpression<String> parameterPublisherName= criteriaBuilder.parameter(String.class);

        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(publisherRoot.get("publisherId"), bookRoot.get("publisherId")),
                criteriaBuilder.equal(genreRoot.get("genreId"), bookRoot.get("genreId")),
                criteriaBuilder.equal(authorRoot.get("authorId"), bookRoot.get("authorId"))
        );

        if (bookTitle != null) {
            predicate = criteriaBuilder.and(
                    criteriaBuilder.like(bookRoot.get("bookTitle"), parameterBookTitle));}

        if (authorFirstName != null){
            predicate= criteriaBuilder.and( criteriaBuilder.like(authorRoot.get("authorFirstName"), parameterAuthorFirstName));}

        if (authorLastName != null){
            predicate= criteriaBuilder.and( criteriaBuilder.like(authorRoot.get("authorLastName"), authorLastName));}

        if (genreName != null){
            predicate= criteriaBuilder.and(criteriaBuilder.like(genreRoot.get("genreName"), parameterGenreName));}

        if (publisherName != null){
            predicate= criteriaBuilder.and(criteriaBuilder.like(publisherRoot.get("publisherName"), parameterPublisherName));}


        criteriaQuery.multiselect(
                bookRoot.get("bookId"),
                bookRoot.get("bookTitle"),
                bookRoot.get("publishingYear"),
                bookRoot.get("availability"),
                publisherRoot.get("publisherName"),
                genreRoot.get("genreName"),
                authorRoot.get("authorFirstName"),
                authorRoot.get("authorLastName")
        ).where(predicate);

        TypedQuery<Object[]> query = entityManager.createQuery(criteriaQuery);
        if(bookTitle != null && !bookTitle.isEmpty()){
            query.setParameter(parameterBookTitle, "%" + bookTitle + "%");
        }
        if(authorFirstName != null){
            query.setParameter(parameterAuthorFirstName, "%" + authorFirstName + "%");
        }
        if(authorLastName != null) {
            query.setParameter(parameterAuthorLastName, "%" + authorLastName + "%");
        }
        if(genreName != null){
            query.setParameter(parameterGenreName, "%" + genreName + "%");
        }
        if(publisherName != null){
            query.setParameter(parameterPublisherName, "%" + publisherName + "%");
        }

        return query.getResultList();

    }
}
