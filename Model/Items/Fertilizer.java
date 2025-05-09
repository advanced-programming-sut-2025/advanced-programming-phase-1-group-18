package Model.Items;

import Model.Name;

public class Fertilizer extends Item implements Name ,Price{

    private String name;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String getCorrectName() {
        return this.name.toLowerCase().replace(" ","");
    }

    @Override
    public int getCorrectPrice() {
        return this.price;
    }
}
