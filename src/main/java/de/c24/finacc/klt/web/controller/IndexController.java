package de.c24.finacc.klt.web.controller;

import de.c24.finacc.klt.exception.FailedRequestException;
import de.c24.finacc.klt.model.Currency;
import de.c24.finacc.klt.service.ExchangeService;
import de.c24.finacc.klt.web.model.ConverterModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * IndexController
 */
@Slf4j
@Controller
public class IndexController {
    private final ExchangeService exchangeService;
    ConverterModel converterModel = new ConverterModel();
    private List<Currency> currencyList = null;

    @Autowired
    public IndexController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    private List<Currency> getCurrencyList() {
        if (this.currencyList == null) {
            try {
                this.currencyList = exchangeService.getCurrencyList();
            } catch (FailedRequestException e) {
                log.error(e.getMessage());
            }
        }
        return currencyList;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        converterModel.setCurrencies(getCurrencyList());
        model.addAttribute("converterModel", converterModel);
        return "index";
    }

    @RequestMapping(value = "/convert", method = RequestMethod.POST)
    public String add(@ModelAttribute("converterModel") ConverterModel converterModel, Model model) throws FailedRequestException {
        model.addAttribute("result", exchangeService.currencyConversion(
                converterModel.getFrom(),
                converterModel.getTo(),
                converterModel.getQuantity())
        );
        converterModel.setCurrencies(getCurrencyList());
        return "index";
    }
}
