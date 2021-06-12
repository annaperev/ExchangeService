package de.c24.finacc.klt.exception;

import de.c24.finacc.klt.dto.ExchangeRatesApiError;

public class ExchangeRatesApiException extends RuntimeException {
    public ExchangeRatesApiException(Integer responseStatus, ExchangeRatesApiError responseBody) {
        super(String.format("status: {}, body: {}", responseStatus, responseBody));
    }
}
