package io.github.group18.Model;

import io.github.group18.Model.Items.FishingPole;

public class StockEntry {
    public FishingPole pole;
    public int quantity;

    public StockEntry(FishingPole pole, int quantity) {
        this.pole = pole;
        this.quantity = quantity;
    }
}
