package edu.esiea.campus_library_flow.domain.exceptions;

public class LoanNotFoundException extends Exception {
    public LoanNotFoundException(String idLoan) {
        super("Le prêt avec l'id ".concat(idLoan).concat(" n'existe pas"));
    }
}
