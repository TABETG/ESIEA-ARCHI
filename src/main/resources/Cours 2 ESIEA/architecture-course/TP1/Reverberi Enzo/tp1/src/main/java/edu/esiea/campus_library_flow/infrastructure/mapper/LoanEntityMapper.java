package edu.esiea.campus_library_flow.infrastructure.mapper;


import edu.esiea.campus_library_flow.domain.Book;
import edu.esiea.campus_library_flow.domain.Loan;
import edu.esiea.campus_library_flow.infrastructure.persistence.entities.BookEntity;
import edu.esiea.campus_library_flow.infrastructure.persistence.entities.LoanEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoanEntityMapper {

    @Mapping(source = "book", target = "bookId")
    Loan toDomain(LoanEntity loan);

    @Mapping(source = "bookId", target = "book")
    LoanEntity toEntity(Loan loan);

    List<Loan> loansToDomain(List<LoanEntity> loans);

    //mapping custom
    default BookEntity map(Long bookId) {
        if (bookId == null) return null;
        BookEntity book = new BookEntity();
        book.setId(bookId);
        return book;
    }

    default Long map(BookEntity book) {
        return book != null ? book.getId() : null;
    }
}
