package com.solaredge.mikhail.beerpub.examples.component.predefined;

import com.solaredge.mikhail.beerpub.components.BeerPriceList;
import com.solaredge.mikhail.beerpub.components.BeerStorage;
import com.solaredge.mikhail.beerpub.model.Beer;

import java.util.Arrays;

public class BeerBar {

    private BeerPriceList beerPriceList;
    private BeerStorage beerStorage;

    public BeerBar(BeerPriceList beerPriceList, BeerStorage beerStorage) {
        this.beerPriceList = beerPriceList;
        this.beerStorage = beerStorage;
    }

    public Beer orderRequest(String type, double size) {
        assertBeerType(type);
        assertBeerRequestedSize(size);
        if (!beerStorage.inStock(type)) {
            beerStorage.orderBeers(type);
            throw new RuntimeException(("Not in stock"));
        }
        return new Beer(type, size, beerPriceList.getPrice(type, size));
    }

    void assertBeerRequestedSize(double size) {
        if (!Arrays.asList(0.5, 0.3).contains(size))
            throw new IllegalArgumentException("Size " + String.valueOf(size) + " not in stock");
    }

    void assertBeerType(String type) {
        if (!Arrays.asList("Goldstar", "Maccabee", "Tuborg", "Carlsberg").contains(type))
            throw new IllegalArgumentException("Type " + String.valueOf(type) + " not in stock");
    }
}
