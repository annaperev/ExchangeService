package de.c24.finacc.klt.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class ExchangeRatesApiCurrencyListResponse {
    private Boolean success;
    private HashMap<String, String> symbols;
}
