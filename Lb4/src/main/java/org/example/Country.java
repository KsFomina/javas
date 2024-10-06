package org.example;

import java.util.Objects;

public class Country {
    private String name;
    private int goldMedals;
    private int silverMedals;
    private int bronzeMedals;

    public Country(String name, int goldMedals, int silverMedals, int bronzeMedals) {
        this.name = name;
        this.goldMedals = goldMedals;
        this.silverMedals = silverMedals;
        this.bronzeMedals = bronzeMedals;
    }

    // Геттеры
    public String getName() {
        return name;
    }

    public int getGoldMedals() {
        return goldMedals;
    }

    public int getSilverMedals() {
        return silverMedals;
    }

    public int getBronzeMedals() {
        return bronzeMedals;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country country)) return false;
        return Objects.equals(name, country.name);
    }
}
