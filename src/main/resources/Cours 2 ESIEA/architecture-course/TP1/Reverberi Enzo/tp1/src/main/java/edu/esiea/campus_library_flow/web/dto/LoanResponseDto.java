package edu.esiea.campus_library_flow.web.dto;

import java.time.LocalDate;

public record LoanResponseDto(Long id,
                              String studentId,
                              Long bookId,
                              LocalDate loanDate,
                              LocalDate returnDate,
                              boolean returned

) {}
