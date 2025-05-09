package Model.Items;

import Model.Name;

import java.util.ArrayList;

public class CraftingItem extends Item implements Name, Price {
    protected String name;
    protected ArrayList<Item> Ingredients;
    protected int SellPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Item> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(ArrayList<Item> ingredients) {
        this.Ingredients = ingredients;
    }

    public int getSellPrice() {
        return SellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.SellPrice = sellPrice;
    }

    @Override
    public String getCorrectName() {
        return name.toLowerCase().replaceAll(" ", "");
    }

    @Override
    public int getCorrectPrice() {
        return this.getSellPrice();
    }
}
