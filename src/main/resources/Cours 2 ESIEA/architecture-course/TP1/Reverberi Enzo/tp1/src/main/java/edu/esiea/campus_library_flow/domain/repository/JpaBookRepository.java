package edu.esiea.campus_library_flow.domain.repository;

import edu.esiea.campus_library_flow.domain.Book;
import edu.esiea.campus_library_flow.infrastructure.mapper.BookEntityMapper;
import edu.esiea.campus_library_flow.infrastructure.persistence.entities.BookEntity;
import edu.esiea.campus_library_flow.infrastructure.repositories.BookJpaEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaBookRepository implements BookRepository {

    private final BookJpaEntityRepository jpa; // Spring Data JPA
    private final BookEntityMapper bookMapper;

    public JpaBookRepository(BookJpaEntityRepository jpa, BookEntityMapper bookMapper) {
        this.jpa = jpa;
        this.bookMapper = bookMapper;
    }

    @Override
    public Book save(Book book) {
        BookEntity bookEntity = bookMapper.toEntity(book);
      return bookMapper.toDomain(jpa.save(bookEntity));
    }

    @Override
    public Optional<Book> findById(long bookId) {
        return jpa.findById(bookId).map(bookMapper::toDomain);
    }

    @Override
    public List<Book> findAll() {
        return bookMapper.booksToDomain(jpa.findAll());
    }

}
