package br.com.fiap.environment.alert.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record AddIndexInPut(

        @NotNull
        @Positive(message = "resource must be bigger than 0")
        @JsonProperty("resource")
        Long id,
        @NotNull
        @Positive(message = "min value must be bigger than 0")
        @JsonProperty("min")
        BigDecimal indMin,
        @NotNull
        @Positive(message = "normal value must be bigger than 0")
        @JsonProperty("normal")
        BigDecimal indNrm,
        @NotNull
        @Positive(message = "max value must be bigger than 0")
        @JsonProperty("max")
        BigDecimal indMax,
        @NotBlank(message = "resource name cannot be blank")
        @JsonProperty("resource_name")
        String name,
        @NotBlank(message = "name name cannot be blank")
        @JsonProperty("unity_measure")
        String unityOfMeasure
) {
}
