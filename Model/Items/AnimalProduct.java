package Model.Items;

import Model.Name;

public class AnimalProduct extends Item implements Name ,Price
{
    protected String Name;
    protected String Jens;
    protected int SellPrice;


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getJens() {
        return Jens;
    }

    public void setJens(String jens) {
        this.Jens = jens;
    }

    public int getSellPrice() {
        return SellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.SellPrice = sellPrice;
    }

    @Override
    public String getCorrectName() {
        return "";
    }

    @Override
    public int getCorrectPrice() {
        return getSellPrice();
    }
}
