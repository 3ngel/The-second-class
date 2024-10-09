package ru.arkhipov.MySecondTestAppSpringBoot.model;

import lombok.Getter;

@Getter
public enum Positions {
    DEV(2.2, false),
    HR(1.2, false),
    TL(2.6, false),
    MG(3.0, true);

    private final double positionCoefficient;
    private final boolean manager;

    Positions(double positionCoefficient, boolean manager){
        this.positionCoefficient = positionCoefficient;
        this.manager = manager;
    }
}
