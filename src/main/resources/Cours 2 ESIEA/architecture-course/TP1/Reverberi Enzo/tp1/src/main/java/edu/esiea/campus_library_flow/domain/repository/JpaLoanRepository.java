package edu.esiea.campus_library_flow.domain.repository;

import edu.esiea.campus_library_flow.domain.Book;
import edu.esiea.campus_library_flow.domain.Loan;
import edu.esiea.campus_library_flow.domain.exceptions.BookNotAvailableException;
import edu.esiea.campus_library_flow.domain.exceptions.BookNotFoundException;
import edu.esiea.campus_library_flow.domain.exceptions.LoanAlreadyReturnedException;
import edu.esiea.campus_library_flow.domain.exceptions.LoanNotFoundException;
import edu.esiea.campus_library_flow.infrastructure.mapper.BookEntityMapper;
import edu.esiea.campus_library_flow.infrastructure.mapper.LoanEntityMapper;
import edu.esiea.campus_library_flow.infrastructure.persistence.entities.LoanEntity;
import edu.esiea.campus_library_flow.infrastructure.repositories.BookJpaEntityRepository;
import edu.esiea.campus_library_flow.infrastructure.repositories.LoanJpaEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaLoanRepository implements LoanRepository {

    private final LoanJpaEntityRepository jpa; // Spring Data JPA
    private final LoanEntityMapper loanMapper;

    public JpaLoanRepository(LoanJpaEntityRepository jpa, LoanEntityMapper loanMapper) {
        this.jpa = jpa;
        this.loanMapper = loanMapper;
    }


    @Override
    public Loan save(Loan loan) {

        LoanEntity loanEntity = loanMapper.toEntity(loan);
        return loanMapper.toDomain(jpa.save(loanEntity));
    }

    @Override
    public Optional<Loan> findById(long loanId) {
        return jpa.findById(loanId).map(loanMapper::toDomain);
    }

    @Override
    public List<Loan> findAllByStudentId(long studentId) {
        return loanMapper.loansToDomain(jpa.getByStudentId(studentId));
    }
}
