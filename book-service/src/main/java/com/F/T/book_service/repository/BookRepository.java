package com.F.T.book_service.repository;

import com.F.T.book_service.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book,String> {
    Optional<Book> findByBookName(String bookName);
    Optional<Book> findByAuthor(String author);
}
