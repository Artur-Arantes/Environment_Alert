package br.com.fiap.environment.alert.dto;

import java.math.BigDecimal;

public record GetIndexOutPut(
        Long id,

        BigDecimal indMin,

        BigDecimal indNrm,

        BigDecimal indMax) {
}
