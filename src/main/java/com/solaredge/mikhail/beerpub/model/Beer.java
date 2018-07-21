package com.solaredge.mikhail.beerpub.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beer {
    private String type; // Goldstar, Maccabee, Carlsberg, Tuborg...
    private double sizeLitre; // 0.3, 0.5
    private double price;
}
