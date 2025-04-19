package Model.Items;

import java.util.ArrayList;

public class CraftingItem extends Item
{
    protected String Name;
    protected ArrayList<Item> Ingredients ;
    protected int SellPrice;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
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
}
