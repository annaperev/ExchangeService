package de.c24.finacc.klt.rest;

import de.c24.finacc.klt.exception.FailedRequestException;
import de.c24.finacc.klt.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Rest Service
 */
@RestController
@RequestMapping("/api")
public class RestService {

    @Autowired
    private ExchangeService exchangeService;

    // TODO: make api methods return structured errors in case of exceptions
    @GetMapping("/convert")
    public BigDecimal convert(@RequestParam String from,
                              @RequestParam String to,
                              @RequestParam BigDecimal quantity) throws FailedRequestException {

        return exchangeService.currencyConversion(from, to, quantity);
    }
}
