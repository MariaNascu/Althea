package com.fortech.repository;

import org.springframework.stereotype.Repository;
import java.util.List;

public interface SelectAllBooksRepository {
   public List<Object[]> selectBookObjects(String bookTitle, String authorFirstName, String authorLastName, String genreName, String publisherName );

}
