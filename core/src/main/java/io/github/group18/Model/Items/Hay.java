package io.github.group18.Model.Items;

import io.github.group18.Model.Name;

public class Hay extends Item implements Name ,Price{
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String getCorrectName() {
        return "hay";
    }

    @Override
    public int getCorrectPrice() {
        return this.price;
    }
}
