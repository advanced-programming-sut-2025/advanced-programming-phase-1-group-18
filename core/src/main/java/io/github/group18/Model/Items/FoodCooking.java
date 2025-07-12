package io.github.group18.Model.Items;

import io.github.group18.Model.Buff;
import io.github.group18.Model.Name;
import io.github.group18.enums.FoodCookingEnums;

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
