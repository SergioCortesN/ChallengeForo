package com.alurachallenges.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRegisterDTO(

        @NotBlank
        String title,

        @NotBlank
        String message,

        @NotNull
        Long authorId,

        @NotNull
        Long courseId

) {}
