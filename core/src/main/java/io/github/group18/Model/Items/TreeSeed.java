package io.github.group18.Model.Items;

import io.github.group18.Model.Name;
import io.github.group18.Model.PictureModel;
import io.github.group18.enums.TreeSeedEnums;

public class TreeSeed extends Seed implements Name, Price, PictureModel {

    TreeSeedEnums type;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public TreeSeedEnums getType() {
        return type;
    }

    public void setType(TreeSeedEnums type) {
        this.type = type;
    }


    @Override
    public String getCorrectName() {
        return type.toString().toLowerCase().replace(" ", "");
    }

    @Override
    public int getCorrectPrice() {
        return this.price;
    }

    @Override
    public String getPath() {
        return "Trees/" + type + ".png";
    }
}
