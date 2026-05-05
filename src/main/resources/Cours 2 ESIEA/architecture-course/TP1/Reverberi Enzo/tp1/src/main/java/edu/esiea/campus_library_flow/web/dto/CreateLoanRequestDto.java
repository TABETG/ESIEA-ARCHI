package edu.esiea.campus_library_flow.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateLoanRequestDto(
        @NotNull(message = "L'étudiant est obligatoire")
        @Positive(message = "L'id de l'étudiant doit être positif")
        Long studentId,
        @NotNull(message = "L'id du livre est obligatoire")
        @Positive(message = "L'id du livre doit être positif")
        Long bookId
) {}
