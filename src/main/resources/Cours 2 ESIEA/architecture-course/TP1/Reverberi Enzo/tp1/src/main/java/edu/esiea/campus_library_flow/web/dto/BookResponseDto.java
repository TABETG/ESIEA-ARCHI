package edu.esiea.campus_library_flow.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record BookResponseDto(@NotBlank String title,
                              @NotBlank String author,
                              @Positive int stock,
                              @NotBlank String isbn
) {}
