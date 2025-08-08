package io.github.group18.Model;


import io.github.group18.Model.Items.CraftingItem;
import io.github.group18.Model.Items.FishingPole;
import io.github.group18.Model.Items.Item;

import java.util.HashMap;

public class FishShopMarket implements adaptMapMarket ,PictureModel{
    HashMap<FishingPole, Integer> Stock = new HashMap<>();

    public HashMap<FishingPole, Integer> getStock() {
        return Stock;
    }

    public void setStock(HashMap<FishingPole, Integer> stock) {
        Stock = stock;
    }

    public void addItem(FishingPole item, int quantity) {
        Stock.put(item, Stock.getOrDefault(item, 0) + quantity);
    }

    public void removeItem(FishingPole item, int quantity) {
        if (Stock.containsKey(item)) {
            int currentQuantity = Stock.get(item);
            if (currentQuantity > quantity) {
                Stock.put(item, currentQuantity - quantity);
            } else {
                Stock.put(item, 0);
            }
        }
    }

    public void fillStock() {
        if (Stock.size() != 0) {
            Stock.clear();
        }
        Stock = new HashMap<>();

//        CraftingItem craftingItem = new CraftingItem("FishSmoker");
//        Stock.put(craftingItem, 1);

        FishingPole f1 = new FishingPole();
        f1.setJens("Bamboo");
        Stock.put(f1, 1);

        FishingPole f2 = new FishingPole();
        f2.setJens("Training");
        Stock.put(f2, 1);

        FishingPole f3 = new FishingPole();
        f3.setJens("Fiberglass");
        Stock.put(f3, 1);

        FishingPole f4 = new FishingPole();
        f4.setJens("Iridium");
        Stock.put(f4, 1);



    }

    @Override
    public String getPath() {
        return "Market/Fish_Shop.png";
    }
}
