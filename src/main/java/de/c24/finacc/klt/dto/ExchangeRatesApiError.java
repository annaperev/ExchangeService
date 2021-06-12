package de.c24.finacc.klt.dto;

import lombok.Data;

@Data
public class ExchangeRatesApiError {
    private Error error;

    @Data
    class Error {
        private String code;
        private String Message;
    }
}
