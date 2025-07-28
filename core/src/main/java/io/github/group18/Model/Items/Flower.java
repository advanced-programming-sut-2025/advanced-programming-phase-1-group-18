package io.github.group18.Model.Items;

import io.github.group18.Model.Name;
import io.github.group18.Model.PictureModel;

public class Flower extends Item implements Name, Price , PictureModel {

    @Override
    public int getCorrectPrice() {
        return 200;
    }

    @Override
    public String getCorrectName() {
        return "flower";
    }

    @Override
    public String getPath() {
        return "flower.png";
    }
}
