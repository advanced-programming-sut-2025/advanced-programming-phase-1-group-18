package Model.Items;

import Model.Name;

public class StoneItem extends Item implements Name, Price {
    protected int price;

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
}