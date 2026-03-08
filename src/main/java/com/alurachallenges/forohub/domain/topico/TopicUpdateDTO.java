package com.alurachallenges.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record TopicUpdateDTO(

        @NotBlank
        String title,

        @NotBlank
        String message

) {}
