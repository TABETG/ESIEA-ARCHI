package edu.esiea.campus_library_flow.web;

import edu.esiea.campus_library_flow.domain.exceptions.BookNotAvailableException;
import edu.esiea.campus_library_flow.domain.exceptions.BookNotFoundException;
import edu.esiea.campus_library_flow.domain.exceptions.LoanAlreadyReturnedException;
import edu.esiea.campus_library_flow.domain.exceptions.LoanNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @RestControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(BookNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ProblemDetail handleNotFound(BookNotFoundException ex) {
            ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
            pd.setTitle("Livre introuvable");
            pd.setDetail(ex.getMessage());
            return pd;
        }

        @ExceptionHandler(LoanNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ProblemDetail handleNotFound(LoanNotFoundException ex) {
            ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
            pd.setTitle("Prêt introuvable");
            pd.setDetail(ex.getMessage());
            return pd;
        }

        @ExceptionHandler(LoanAlreadyReturnedException.class)
        @ResponseStatus(HttpStatus.CONFLICT)
        public ProblemDetail handleNotAvailable(LoanAlreadyReturnedException ex) {
            ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.CONFLICT);
            pd.setTitle("Prêt a déjà été rendu");
            pd.setDetail(ex.getMessage());
            return pd;
        }

        @ExceptionHandler(BookNotAvailableException.class)
        @ResponseStatus(HttpStatus.CONFLICT)
        public ProblemDetail handleNotAvailable(BookNotAvailableException ex) {
            ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.CONFLICT);
            pd.setTitle("Livre indisponible");
            pd.setDetail(ex.getMessage());
            return pd;
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {
            ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
            pd.setTitle("Données invalides");
            Map<String, String> errors = new LinkedHashMap<>();
            ex.getBindingResult().getFieldErrors()
                    .forEach(fe -> errors.put(fe.getField(), fe.getDefaultMessage()));
            pd.setProperty("errors", errors);
            return pd;
        }
    }
}
