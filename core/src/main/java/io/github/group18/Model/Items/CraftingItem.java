package io.github.group18.Model.Items;

import io.github.group18.enums.CraftingRecipesEnums;

import java.util.HashMap;
import io.github.group18.Model.Name;

public class CraftingItem extends Item implements Name,Price
{
    public CraftingRecipesEnums craftingItem;
    public String Name;
    public HashMap<String,Integer> Ingredients ;
    public int SellPrice;
    public int price;
    public boolean isProcessing;
    public ArtisanGoods insideArtisan;
    public boolean isReady;

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
        return craftingItem.name();
    }


    @Override
    public int getCorrectPrice() {
        return SellPrice;
    }

    public boolean isProcessing() {
        return isProcessing;
    }

    public void setProcessing(boolean processing) {
        isProcessing = processing;
    }

    public ArtisanGoods getInsideArtisan() {
        return insideArtisan;
    }

    public void setInsideArtisan(ArtisanGoods insideArtisan) {
        this.insideArtisan = insideArtisan;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
}
