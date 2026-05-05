package edu.esiea.campus_library_flow.web.mapper;

import edu.esiea.campus_library_flow.domain.Book;
import edu.esiea.campus_library_flow.domain.Loan;
import edu.esiea.campus_library_flow.infrastructure.persistence.entities.BookEntity;
import edu.esiea.campus_library_flow.web.dto.BookResponseDto;
import edu.esiea.campus_library_flow.web.dto.CreateBookRequestDto;
import edu.esiea.campus_library_flow.web.dto.CreateLoanRequestDto;
import edu.esiea.campus_library_flow.web.dto.LoanResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    LoanResponseDto toDto(Loan loan);

    Loan toEntity(CreateLoanRequestDto dto);

    List<LoanResponseDto> loanToDto(List<Loan> loans);


}
