package io.github.group18.Model.Items;

import io.github.group18.Model.Name;

public class Flower extends Item implements Name, Price {

    @Override
    public int getCorrectPrice() {
        return 200;
    }

    @Override
    public String getCorrectName() {
        return "flower";
    }
}
