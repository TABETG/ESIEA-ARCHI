package edu.esiea.campus_library_flow.application;

import edu.esiea.campus_library_flow.domain.Book;
import edu.esiea.campus_library_flow.domain.Loan;
import edu.esiea.campus_library_flow.domain.exceptions.BookNotAvailableException;
import edu.esiea.campus_library_flow.domain.exceptions.BookNotFoundException;
import edu.esiea.campus_library_flow.domain.exceptions.LoanAlreadyReturnedException;
import edu.esiea.campus_library_flow.domain.exceptions.LoanNotFoundException;
import edu.esiea.campus_library_flow.domain.repository.BookRepository;
import edu.esiea.campus_library_flow.domain.repository.LoanRepository;
import edu.esiea.campus_library_flow.web.dto.BookResponseDto;
import edu.esiea.campus_library_flow.web.dto.CreateBookRequestDto;
import edu.esiea.campus_library_flow.web.dto.CreateLoanRequestDto;
import edu.esiea.campus_library_flow.web.dto.LoanResponseDto;
import edu.esiea.campus_library_flow.web.mapper.BookMapper;
import edu.esiea.campus_library_flow.web.mapper.LoanMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@Transactional
public class LoanApplicationService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final LoanMapper loanMapper;

    public LoanApplicationService(LoanRepository loanRepository, LoanMapper loanMapper, BookRepository bookRepository) {
        this.loanRepository = loanRepository; // Spring injecte JpaBookRepository automatiquement
        this.bookRepository = bookRepository;
        this.loanMapper = loanMapper;
    }

    @Transactional
    public LoanResponseDto createLoan(CreateLoanRequestDto createLoanRequestDto) throws BookNotFoundException, BookNotAvailableException {

        Book book = bookRepository.findById(createLoanRequestDto.bookId())
                .orElseThrow(() -> new BookNotFoundException(String.valueOf(createLoanRequestDto.bookId())));
        book.decrementStock();

        Loan loan = loanMapper.toEntity(createLoanRequestDto);
        loanRepository.save(loan);
        bookRepository.save(book);

        return loanMapper.toDto(loan);
    }

    @Transactional
    public LoanResponseDto closeLoan(long loanId) throws BookNotFoundException, BookNotAvailableException, LoanAlreadyReturnedException, LoanNotFoundException {

        // récupérer le loan
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException(String.valueOf(loanId)));

        // récupérer le book
        Book book = bookRepository.findById(loan.getBookId())
                .orElseThrow(() -> new BookNotFoundException(String.valueOf(loan.getBookId())));

        // logique métier
        loan.markAsReturned();
        book.incrementStock();

        // persistance
        loanRepository.save(loan);
        bookRepository.save(book);

        return loanMapper.toDto(loan);
    }

    public LoanResponseDto getById(long loanId)throws BookNotFoundException {
        return loanRepository.findById(loanId).map(loanMapper::toDto)
                .orElseThrow(() -> new BookNotFoundException(String.valueOf(loanId)));
    }

    public List<LoanResponseDto> findByStudentId(long studentId) {
        return loanMapper.loanToDto(loanRepository.findAllByStudentId(studentId));
    }

}
