package br.com.fiap.environment.alert.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AddMeasureInputDto(

        @NotNull
        @Positive
        long resource,

        @NotBlank
        String location,

        @NotNull
        BigDecimal measurement,

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        @JsonProperty("date_time")
        @NotNull
        LocalDateTime dateTime
) {
}
