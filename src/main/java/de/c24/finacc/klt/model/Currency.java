package de.c24.finacc.klt.model;

import lombok.Data;

@Data
public class Currency {
    private String id;
    private String name;

    public Currency(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
