package Model.Items;

import enums.CraftingRecipesEnums;

import java.util.ArrayList;
import java.util.HashMap;
import Model.Name;

public class CraftingItem extends Item implements Name,Price
{
    protected CraftingRecipesEnums craftingItem;
    protected String Name;
    protected HashMap<String,Integer> Ingredients ;
    protected int SellPrice;
    protected int price;

    public CraftingItem(String name) {
        for(CraftingRecipesEnums craft : CraftingRecipesEnums.values()){
            if (craft.name().equals(name)){
                this.craftingItem = craft;
                this.Name = craft.name();
                this.Ingredients = craft.getIngredients();
                this.SellPrice = craft.getPrice();
                break;
            }
        }
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public CraftingRecipesEnums getCraftingItem() {
        return craftingItem;
    }

    public HashMap<String, Integer> getIngredients() {
        return Ingredients;
    }

    public int getSellPrice() {
        return SellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.SellPrice = sellPrice;
    }
    @Override
    public String getCorrectName() {
        return craftingItem.name().toLowerCase().replace(" ", "");
    }


    @Override
    public int getCorrectPrice() {
        return SellPrice;
    }
}