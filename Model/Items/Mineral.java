package Model.Items;

import enums.ForagingMineralsEnums;

public class Mineral extends Item{
    private ForagingMineralsEnums type;
    private int price;
    public ForagingMineralsEnums getType() {
        return type;
    }

    public void setType(ForagingMineralsEnums type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
