package de.c24.finacc.klt.service;

import de.c24.finacc.klt.dto.ExchangeRatesApiConvertResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ExchangeServiceTest {

    @Autowired
    private ExchangeService exchangeService;

    @MockBean
    private ExchangeRatesApiProxy exchangeRatesApiProxy;

    @Test
    void currencyConversion() {
        ExchangeRatesApiConvertResponse mockResponse = ExchangeRatesApiConvertResponse.builder()
                .success(true)
                .base("EUR")
                .date("2021-06-12")
                .rates(Map.of(
                        "USD", BigDecimal.valueOf(1.21085),
                        "EUR", BigDecimal.valueOf(1)
                ))
                .build();
        when(exchangeRatesApiProxy.getExchangeRatesToEur(new String[]{"USD", "EUR"})).thenReturn(mockResponse);

        BigDecimal converted = exchangeService.currencyConversion("USD", "EUR", BigDecimal.valueOf(100));
        assertEquals(converted.compareTo(BigDecimal.valueOf(82.586600)), 0);
    }

    // todo add more tests for negative scenarios
}