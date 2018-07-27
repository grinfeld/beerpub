package com.mikerusoft.mikhail.beerpub.examples.component.predefined;

import com.mikerusoft.mikhail.beerpub.components.BeerPriceList;
import com.mikerusoft.mikhail.beerpub.model.Beer;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BeerPriceListImpl implements BeerPriceList {

    private static Map<String, Map<Double, Double>> prices = new ConcurrentHashMap<>();
    static {
        prices.putAll(Stream.of(
                Beer.builder().type("Goldstar").sizeLitre(0.3).price(14).build(),
                Beer.builder().type("Goldstar").sizeLitre(0.5).price(20).build(),
                Beer.builder().type("Maccabee").sizeLitre(0.3).price(12).build(),
                Beer.builder().type("Maccabee").sizeLitre(0.5).price(16).build(),
                Beer.builder().type("Tuborg").sizeLitre(0.3).price(14).build(),
                Beer.builder().type("Tuborg").sizeLitre(0.5).price(22).build(),
                Beer.builder().type("Carlsberg").sizeLitre(0.3).price(13).build(),
                Beer.builder().type("Carlsberg").sizeLitre(0.5).price(19).build()
        ).collect(Collectors.groupingBy(
                Beer::getType,
                Collectors.toMap(Beer::getSizeLitre, Beer::getPrice)
        )));
    }

    @Override
    public double getPrice(String type, double sizeLitre) {
        Map<Double, Double> specificTypePrices =
                Optional.ofNullable(prices.get(type)).
                        orElseThrow(() -> new IllegalArgumentException("No such type"));

        return Optional.ofNullable(specificTypePrices.get(sizeLitre))
                .orElseThrow(() -> new IllegalArgumentException("No such size"));
    }
}
