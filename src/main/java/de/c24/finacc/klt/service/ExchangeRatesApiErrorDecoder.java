package de.c24.finacc.klt.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.c24.finacc.klt.dto.ExchangeRatesApiError;
import de.c24.finacc.klt.exception.ExchangeRatesApiException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class ExchangeRatesApiErrorDecoder implements ErrorDecoder {
    private final ObjectMapper objectMapper;

    public ExchangeRatesApiErrorDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        ExchangeRatesApiError body = null;
        Response.Body responseBody = response.body();
        if (responseBody != null) {
            try {
                body = objectMapper.readValue(responseBody.asInputStream(), ExchangeRatesApiError.class);
            } catch (IOException e) {
                log.error("{}", e);
            }
        }

        Integer responseStatus = response.status();

        throw new ExchangeRatesApiException(responseStatus, body);
    }
}
