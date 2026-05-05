package edu.esiea.campus_library_flow.domain;
import edu.esiea.campus_library_flow.domain.exceptions.LoanAlreadyReturnedException;

import java.time.LocalDate;

public class Loan {

    private final Long id;
    private final String studentId;
    private final Long bookId;
    private final LocalDate loanDate;
    private LocalDate returnDate;
    private boolean returned = false;

    public Loan(Long id, String studentId, Long bookId, LocalDate returnDate, boolean returned) {
        this.id = id;
        this.studentId = studentId;
        this.bookId = bookId;
        this.loanDate = LocalDate.now();
        this.returnDate = returnDate;
        this.returned = returned;
    }

    // logique métier
    public void markAsReturned() throws LoanAlreadyReturnedException {
        if (this.returned) {
            throw new LoanAlreadyReturnedException(String.valueOf(this.id));
        }
        this.returned = true;
        this.returnDate = LocalDate.now();
    }

    // Getters

    public Long getId() { return id; }
    public String getStudentId() { return studentId; }
    public Long getBookId() { return bookId; }
    public LocalDate getLoanDate() { return loanDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public boolean isReturned() { return returned; }
}