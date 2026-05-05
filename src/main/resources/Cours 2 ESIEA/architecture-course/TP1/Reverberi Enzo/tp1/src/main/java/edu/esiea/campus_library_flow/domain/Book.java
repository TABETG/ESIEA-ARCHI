package edu.esiea.campus_library_flow.domain;

import edu.esiea.campus_library_flow.domain.exceptions.BookNotAvailableException;

// Entité riche : porte ses invariants et ses règles métier
public class Book {
    private final Long id;
    private final String title;
    private final String author;
    private       int    stock;
    private final String isbn;

    public Book(Long id, String title, String author, int stock, String isbn) {
        if (stock < 0) throw new IllegalArgumentException("Stock négatif interdit");
        this.id     = id;
        this.title  = title;
        this.author = author;
        this.stock  = stock;
        this.isbn   = isbn;
    }

    // Méthode métier — encapsule la règle "un emprunt décrémente le stock"
    public void decrementStock() throws BookNotAvailableException {
        if (this.stock <= 0) {
            throw new BookNotAvailableException(this.id);
        }
        this.stock--;
    }

    public void incrementStock() { this.stock++; }

    // Getters (pas de setters publics — encapsulation)
    public Long   getId()     { return id;     }
    public int    getStock()  { return stock;  }
    public String getTitle()  { return title;  }
    public String getAuthor() { return author; }
    public String getIsbn()   { return isbn;   }
}
