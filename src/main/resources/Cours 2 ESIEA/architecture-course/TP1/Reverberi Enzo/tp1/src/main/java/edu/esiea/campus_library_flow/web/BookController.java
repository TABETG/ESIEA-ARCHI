package edu.esiea.campus_library_flow.web;

import edu.esiea.campus_library_flow.application.BookApplicationService;
import edu.esiea.campus_library_flow.domain.exceptions.BookNotFoundException;
import edu.esiea.campus_library_flow.web.dto.BookResponseDto;
import edu.esiea.campus_library_flow.web.dto.CreateBookRequestDto;
import edu.esiea.campus_library_flow.web.mapper.BookMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookApplicationService bookApplicationService;  // dépend du PORT, pas de l'impl

    public BookController(BookApplicationService bookApplicationService) {
        this.bookApplicationService = bookApplicationService;
    }

    @GetMapping
    @Operation(summary = "Récupère tous les livres")
    public List<BookResponseDto> getAllLessons() {
        return bookApplicationService.getAllLessons();
    }

    @GetMapping("/{bookId}")
    @Operation(summary = "récupère un livre grâce à l'id")
    public BookResponseDto getLessonById(@PathVariable @Positive Long bookId) throws BookNotFoundException {
        return bookApplicationService.findById(bookId);
    }

    @PostMapping
    @Operation(summary = "Créer un nouveau livre")
    public BookResponseDto create(@Valid @RequestBody CreateBookRequestDto req) {
        return bookApplicationService.createBook(req);
    }
}
