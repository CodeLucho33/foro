package com.foro.dto;

import java.util.Date;

public record TopicResponseDTO(
        Long idTopic,
        String title,
        String message,
        Date dateCreated,
        boolean status,
        String authorName,       // Nombre del autor (no el objeto User completo)
        String courseName        // Nombre del curso (no el objeto Course completo)
) {}