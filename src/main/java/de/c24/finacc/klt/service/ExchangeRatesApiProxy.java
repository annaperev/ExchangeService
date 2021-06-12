package de.c24.finacc.klt.service;

import de.c24.finacc.klt.config.ExchangeRatesApiClientConfig;
import de.c24.finacc.klt.dto.ExchangeRatesApiConvertResponse;
import de.c24.finacc.klt.dto.ExchangeRatesApiCurrencyListResponse;
import feign.RequestLine;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "exchangeratesapi", url = "http://api.exchangeratesapi.io/v1",
        configuration = ExchangeRatesApiClientConfig.class)
public interface ExchangeRatesApiProxy {

    // TODO setup different cache policy for getcurrerncyList and getrate
    @Cacheable(value = "ExchangeRatesApiCache")
    @RequestLine("GET /symbols?access_key=0daab3fd0ab5833576985af20b03bd0c")
    ExchangeRatesApiCurrencyListResponse getCurrencyList();

    @Cacheable(value = "ExchangeRatesApiCache")
    @RequestLine("GET /latest?access_key=0daab3fd0ab5833576985af20b03bd0c")
    ExchangeRatesApiConvertResponse getExchangeRatesToEur(@RequestParam("symbols[]") String[] currencies);
}
