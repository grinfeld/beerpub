package com.mikerusoft.mikhail.beerpub.components;

public interface BeerStorage {
    boolean inStock(String type);
    void orderBeers(String type);
}
