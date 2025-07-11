package io.githubgroup18.Model.Items;
import io.githubgroup18.Model.Name;

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
