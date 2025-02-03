package com.foro.dto;

import com.foro.model.Course;
import com.foro.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRequestDTO(
        @NotBlank String title,
        @NotBlank String message,
        @NotNull Long authorID, // Ahora es un Long en vez de User
        @NotNull Long courseId  // Ahora es un Long en vez de Course
) {
}
