package com.alurachallenges.forohub.domain.topico;

import java.time.LocalDateTime;

public record TopicResponseDTO(
        Long id,
        String title,
        String message,
        LocalDateTime createdAt,
        String status,
        String author,
        String course
) {}
