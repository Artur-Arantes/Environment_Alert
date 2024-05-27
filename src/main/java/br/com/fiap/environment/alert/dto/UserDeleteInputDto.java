package br.com.fiap.environment.alert.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;

public record UserDeleteInputDto(
        @Positive
        Long id,
        @Email(message = "must be an email on a valid format")
        String email
) {
}
