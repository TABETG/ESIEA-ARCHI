package edu.esiea.campus_library_flow.domain.exceptions;

public class LoanAlreadyReturnedException extends Exception {
    public LoanAlreadyReturnedException(String idLoan) {
        super("Le prêt avec l'id".concat(idLoan).concat(" a déjà été rendu"));
    }
}
