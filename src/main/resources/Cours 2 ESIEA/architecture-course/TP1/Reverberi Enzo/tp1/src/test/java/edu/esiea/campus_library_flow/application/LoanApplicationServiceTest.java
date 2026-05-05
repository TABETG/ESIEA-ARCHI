package edu.esiea.campus_library_flow.application;

import edu.esiea.campus_library_flow.domain.Book;
import edu.esiea.campus_library_flow.domain.Loan;
import edu.esiea.campus_library_flow.domain.exceptions.BookNotAvailableException;
import edu.esiea.campus_library_flow.domain.exceptions.BookNotFoundException;
import edu.esiea.campus_library_flow.domain.exceptions.LoanAlreadyReturnedException;
import edu.esiea.campus_library_flow.domain.exceptions.LoanNotFoundException;
import edu.esiea.campus_library_flow.domain.repository.BookRepository;
import edu.esiea.campus_library_flow.domain.repository.LoanRepository;
import edu.esiea.campus_library_flow.infrastructure.persistence.entities.LoanEntity;
import edu.esiea.campus_library_flow.web.dto.CreateLoanRequestDto;
import edu.esiea.campus_library_flow.web.dto.LoanResponseDto;
import edu.esiea.campus_library_flow.web.mapper.LoanMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class LoanApplicationServiceTest {
    @Mock
    private LoanRepository loanRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private LoanMapper loanMapper;

    @InjectMocks
    private LoanApplicationService service;

    @Test
    void should_create_loan_when_book_available() throws BookNotFoundException, BookNotAvailableException {

        Book book = new Book(1L, "title", "author", 10,"isbn");
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Loan loan = new Loan(1L, "1", 1L, null, false);
        Mockito.when(loanMapper.toEntity(Mockito.any()))
                .thenReturn(loan);
        LoanResponseDto loanResponseDto = new LoanResponseDto(1L, "1",1L, LocalDate.now(), null, false);
        Mockito.when(loanMapper.toDto(Mockito.any()))
                .thenReturn(loanResponseDto);
        CreateLoanRequestDto createLoanRequestDto = new CreateLoanRequestDto(1L, 1L);

        LoanResponseDto newLoanResponseDto = service.createLoan(createLoanRequestDto);



        // verifier
        assertNotNull(loan);
        assertEquals("1", newLoanResponseDto.studentId());
        Mockito.verify(bookRepository).save(book); // stock décrémenté
        Mockito.verify(loanRepository).save(Mockito.any());
    }


    @Test
    void should_throw_when_book_not_found() {
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        CreateLoanRequestDto createLoanRequestDto = new CreateLoanRequestDto(1L, 1L);
        assertThrows(BookNotFoundException.class,
                () -> service.createLoan(createLoanRequestDto));
    }

    @Test
    void should_throw_when_no_stock() {
        Book book = new Book(1L, "title", "author", 0, "isbn");
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        CreateLoanRequestDto createLoanRequestDto = new CreateLoanRequestDto(1L, 1L);
        assertThrows(BookNotAvailableException.class,
                () -> service.createLoan(createLoanRequestDto));
    }

    @Test
    void should_return_loan() throws BookNotFoundException, BookNotAvailableException, LoanAlreadyReturnedException, LoanNotFoundException {
        Loan loan = new Loan(1L, "student1", 1L, LocalDate.now(), false);
        Book book = new Book(1L, "title", "author", 10, "isbn");

        Mockito.when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        service.closeLoan(1L);

        assertTrue(loan.isReturned());
        Mockito.verify(bookRepository).save(book); // stock +1
    }
}
