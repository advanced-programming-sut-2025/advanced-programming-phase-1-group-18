package Model.Items;

import Model.Name;
import enums.ForagingMineralsEnums;

public class Mineral extends Item implements Name ,Price{
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

    @Override
    public String getCorrectName() {
        return type.toString().toLowerCase().replace(" ","");
    }

    @Override
    public int getCorrectPrice() {
        return this.price;
    }
}
