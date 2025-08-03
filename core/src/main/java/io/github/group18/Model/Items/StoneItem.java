package io.github.group18.Model.Items;

import io.github.group18.Model.Name;
import io.github.group18.Model.PictureModel;

public class StoneItem extends Item implements Name, Price, PictureModel {
    public int price;

    @Override
    public String getCorrectName() {
        return "stone";
    }


    @Override
    public int getCorrectPrice() {
        return price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String getPath() {
        return "Mineral/Marble.png";
    }
}
