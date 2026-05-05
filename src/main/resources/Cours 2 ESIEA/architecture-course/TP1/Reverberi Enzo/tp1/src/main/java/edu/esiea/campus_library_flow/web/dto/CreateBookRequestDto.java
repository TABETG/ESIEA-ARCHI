package edu.esiea.campus_library_flow.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateBookRequestDto(
        @NotBlank(message = "Le titre est obligatoire") String title,
        @NotBlank(message = "L'auteur est obligatoire") String author,
        @Positive(message = "Le stock doit être positif")
        @Max(value = 1000, message = "Stock limité à 1000") int stock,
        @NotBlank(message = "Le numéro isbn est obligatoire") String isbn
) {}