package edu.esiea.campus_library_flow.web;
import edu.esiea.campus_library_flow.application.LoanApplicationService;
import edu.esiea.campus_library_flow.domain.exceptions.BookNotAvailableException;
import edu.esiea.campus_library_flow.domain.exceptions.BookNotFoundException;
import edu.esiea.campus_library_flow.domain.exceptions.LoanAlreadyReturnedException;
import edu.esiea.campus_library_flow.domain.exceptions.LoanNotFoundException;
import edu.esiea.campus_library_flow.web.dto.CreateLoanRequestDto;
import edu.esiea.campus_library_flow.web.dto.LoanResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {

    private final LoanApplicationService loanApplicationService;  // dépend du PORT, pas de l'impl

    public LoanController(LoanApplicationService loanApplicationService) {
        this.loanApplicationService = loanApplicationService;
    }

    @GetMapping("/{loanId}")
    @Operation(summary = "Récupère un prêt par son Id")
    public LoanResponseDto findLoanById(@PathVariable @Positive Long loanId) throws BookNotFoundException {
        return loanApplicationService.getById(loanId);
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "récupère un emprunt par l'id d'un étudiant")
    public List<LoanResponseDto> findLoanByStudentId(@PathVariable @Positive Long studentId)  {
        return loanApplicationService.findByStudentId(studentId);
    }

    @PostMapping
    @Operation(summary = "Créer un nouveau emprunt")
    public LoanResponseDto create(@Valid @RequestBody CreateLoanRequestDto req) throws BookNotFoundException, BookNotAvailableException {
        return loanApplicationService.createLoan(req);
    }

    @PostMapping("/close/{loanId}")
    @Operation(summary = "Cloture un emprunt")
    public LoanResponseDto close(@PathVariable @Positive Long loanId) throws BookNotFoundException, BookNotAvailableException, LoanAlreadyReturnedException, LoanNotFoundException {
        return loanApplicationService.closeLoan(loanId);
    }

}
