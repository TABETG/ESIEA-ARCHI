package edu.esiea.campus_library_flow.domain.repository;

import edu.esiea.campus_library_flow.domain.Book;
import edu.esiea.campus_library_flow.domain.Loan;
import edu.esiea.campus_library_flow.domain.exceptions.BookNotAvailableException;
import edu.esiea.campus_library_flow.domain.exceptions.BookNotFoundException;
import edu.esiea.campus_library_flow.domain.exceptions.LoanAlreadyReturnedException;
import edu.esiea.campus_library_flow.domain.exceptions.LoanNotFoundException;

import java.util.List;
import java.util.Optional;

public interface LoanRepository {
    Loan save(Loan loan) throws BookNotAvailableException, BookNotFoundException;
    Optional<Loan> findById(long loanId);
    List<Loan> findAllByStudentId(long studentId);
}
