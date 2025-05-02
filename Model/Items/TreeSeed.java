package Model.Items;

import enums.TreeSeedEnums;

public class TreeSeed extends Seed{

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
}
