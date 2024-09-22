package org.example;

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
}
