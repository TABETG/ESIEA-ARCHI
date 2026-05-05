package edu.esiea.campus_library_flow.domain.repository;

import edu.esiea.campus_library_flow.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    Optional<Book> findById(long bookId);
    List<Book> findAll();
}
