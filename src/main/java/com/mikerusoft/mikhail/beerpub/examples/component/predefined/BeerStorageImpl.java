package com.mikerusoft.mikhail.beerpub.examples.component.predefined;

import com.mikerusoft.mikhail.beerpub.components.BeerStorage;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class BeerStorageImpl implements BeerStorage {

    private static Map<String, Boolean> inStock = new ConcurrentHashMap<>();
    static {
        inStock.put("Goldstar", true);
        inStock.put("Maccabee", false);
        inStock.put("Tuborg", true);
        inStock.put("Carlsberg", false);
    }

    @Override
    public boolean inStock(String type) {
        return Optional.ofNullable(inStock.get(type)).orElse(false);
    }

    @Override
    public void orderBeers(String type) {
        // do something
    }
}
