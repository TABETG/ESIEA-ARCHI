package edu.esiea.campus_library_flow.application;

import edu.esiea.campus_library_flow.domain.Book;
import edu.esiea.campus_library_flow.domain.exceptions.BookNotAvailableException;
import edu.esiea.campus_library_flow.domain.exceptions.BookNotFoundException;
import edu.esiea.campus_library_flow.domain.repository.BookRepository;
import edu.esiea.campus_library_flow.web.dto.BookResponseDto;
import edu.esiea.campus_library_flow.web.dto.CreateBookRequestDto;
import edu.esiea.campus_library_flow.web.mapper.BookMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class BookApplicationService  {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookApplicationService(BookRepository bookRepo, BookMapper bookMapper) {
        this.bookRepository = bookRepo; // Spring injecte JpaBookRepository automatiquement
        this.bookMapper = bookMapper;
    }

    @Transactional
    public BookResponseDto createBook(CreateBookRequestDto createBookRequestDto) {
        Book book = bookMapper.toEntity(createBookRequestDto);
        book = bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    @GetMapping
    public List<BookResponseDto> getAllLessons() {
        return bookMapper.booksToDto(bookRepository.findAll());
    }

    public BookResponseDto findById(long bookId) throws BookNotFoundException {
        return bookRepository.findById(bookId).map(bookMapper::toDto)
                .orElseThrow(() -> new BookNotFoundException(String.valueOf(bookId)));
    }

}
