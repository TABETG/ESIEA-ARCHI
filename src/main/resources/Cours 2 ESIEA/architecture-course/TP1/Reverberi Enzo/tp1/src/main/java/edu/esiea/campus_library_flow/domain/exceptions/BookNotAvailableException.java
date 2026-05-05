package edu.esiea.campus_library_flow.domain.exceptions;

public class BookNotAvailableException extends Exception {
    public BookNotAvailableException(Long id) {
        super("Le livre ".concat(String.valueOf(id)).concat("N'est pas disponible"));
    }
}
