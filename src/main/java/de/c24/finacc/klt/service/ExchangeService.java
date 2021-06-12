package de.c24.finacc.klt.service;

import de.c24.finacc.klt.dto.ExchangeRatesApiConvertResponse;
import de.c24.finacc.klt.dto.ExchangeRatesApiCurrencyListResponse;
import de.c24.finacc.klt.exception.FailedRequestException;
import de.c24.finacc.klt.model.Currency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ExchangeService {
    @Autowired
    private ExchangeRatesApiProxy exchangeRatesApiProxy;

    public List<Currency> getCurrencyList() throws FailedRequestException {
        ExchangeRatesApiCurrencyListResponse currencyListResponse = exchangeRatesApiProxy.getCurrencyList();
        if (!currencyListResponse.getSuccess()) {
            log.info("received not successful response status for getCurrencyList");
            throw new FailedRequestException();
        }

        return currencyListResponse.getSymbols().entrySet().stream().sorted(Map.Entry.comparingByKey())
                .map(stringStringEntry ->
                        new Currency(stringStringEntry.getKey(), stringStringEntry.getValue())
                ).collect(Collectors.toList());
    }

    public BigDecimal currencyConversion(String from,
                                         String to,
                                         BigDecimal quantity) {
        if (from.equals(to)) {
            return quantity;
        }

        ExchangeRatesApiConvertResponse exchangeRatesApiConvertResponse =
                exchangeRatesApiProxy.getExchangeRatesToEur(new String[]{from, to});
        if (!exchangeRatesApiConvertResponse.getSuccess()) {
            log.info("received not successful response status for getExchangeRatesToEur [{},{}]", from, to);
            throw new FailedRequestException();
        }

        BigDecimal rateFrom = exchangeRatesApiConvertResponse.getRates().get(from);
        BigDecimal rateTo = exchangeRatesApiConvertResponse.getRates().get(to);

        return rateTo.divide(rateFrom, 6, RoundingMode.HALF_EVEN).multiply(quantity)
                .setScale(6, RoundingMode.HALF_EVEN);
    }
}
