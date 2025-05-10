package Model.Items;

import Model.Buff;
import Model.Name;
import enums.FoodCookingEnums;

import java.util.ArrayList;

public class FoodCooking extends Item implements Name, Price {

    protected FoodCookingEnums name;
    protected int energy;
    protected int sellPrice;
    protected Buff buff;

    public FoodCookingEnums getNamee() {
        return name;
    }

    public void setName(FoodCookingEnums name) {
        this.name = name;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Buff getBuff() {
        return buff;
    }

    public void setBuff(Buff buff) {
        this.buff = buff;
    }

    @Override
    public String getCorrectName() {
        return this.name.toString().toLowerCase().replace(" ", "");
    }

    @Override
    public int getCorrectPrice() {
        return this.sellPrice;
    }
}
