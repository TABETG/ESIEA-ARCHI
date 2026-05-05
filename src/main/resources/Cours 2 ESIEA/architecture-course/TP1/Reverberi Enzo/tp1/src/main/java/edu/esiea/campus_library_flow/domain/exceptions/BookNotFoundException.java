package edu.esiea.campus_library_flow.domain.exceptions;

public class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super("Le livre ".concat(message).concat(" n'existe pas"));
    }
}
