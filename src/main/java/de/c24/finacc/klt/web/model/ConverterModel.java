package de.c24.finacc.klt.web.model;

import de.c24.finacc.klt.model.Currency;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ConverterModel {
    private BigDecimal quantity = BigDecimal.valueOf(1);
    private String from;
    private String to;
    private List<Currency> currencies;

    private String title = "Karten&Konten KLT";
    private String welcome = "Welcome to Check24";
    private String applicationTitle = "Currency Conversion";
}
