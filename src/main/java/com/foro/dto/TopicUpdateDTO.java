package com.foro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicUpdateDTO(
        @NotBlank String title,
        @NotBlank String message,
        @NotNull boolean status,
        @NotNull Long authorId,  // ID del autor (opcional, si se permite cambiar el autor)
        @NotNull Long courseId   // ID del curso (opcional, si se permite cambiar el curso)
) {}