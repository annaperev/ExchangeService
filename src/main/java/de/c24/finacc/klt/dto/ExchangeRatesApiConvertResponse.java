package de.c24.finacc.klt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRatesApiConvertResponse {
    private Boolean success;
    private String base;
    private String date;
    private Map<String, BigDecimal> rates;
}