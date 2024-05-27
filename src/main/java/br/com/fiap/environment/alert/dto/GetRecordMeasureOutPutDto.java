package br.com.fiap.environment.alert.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GetRecordMeasureOutPutDto(
        Long resource,
        @JsonProperty("date_time")
        LocalDateTime dateTime,
        String location,
        BigDecimal measure
) {
}
