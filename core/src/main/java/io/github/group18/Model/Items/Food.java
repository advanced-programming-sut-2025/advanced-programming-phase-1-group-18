package io.github.group18.Model.Items;

import io.github.group18.Model.Name;
import io.github.group18.Model.PictureModel;

public class Food extends Item implements Name, Price, PictureModel {
    private String name;
    private int price;


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getCorrectName() {
        return this.name.toLowerCase().replace(" ", "");
    }

    @Override
    public int getCorrectPrice() {
        return this.price;
    }

    @Override
    public String getPath() {
        return getCorrectName().replace(" ", "") + ".jpg";
    }
}
