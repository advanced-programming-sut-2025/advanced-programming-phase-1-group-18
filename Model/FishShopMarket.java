package Model;

import Model.Items.*;
import enums.ForagingMineralsEnums;
import enums.Seasons;

import java.util.HashMap;

public class FishShopMarket implements adaptMapMarket {
    HashMap<Item, Integer> Stock = new HashMap<>();

    public HashMap<Item, Integer> getStock() {
        return Stock;
    }

    public void setStock(HashMap<Item, Integer> stock) {
        Stock = stock;
    }

    public void addItem(Item item, int quantity) {
        Stock.put(item, Stock.getOrDefault(item, 0) + quantity);
    }

    public void removeItem(Item item, int quantity) {
        if (Stock.containsKey(item)) {
            int currentQuantity = Stock.get(item);
            if (currentQuantity > quantity) {
                Stock.put(item, currentQuantity - quantity);
            } else {
                Stock.remove(item);
            }
        }
    }

    public void fillStock() {
        if (Stock.size() != 0) {
            Stock.clear();
        }
        Stock = new HashMap<>();

        CraftingItem craftingItem = new CraftingItem("FishSmoker");
        Stock.put(craftingItem, 1);


        //TODO trout soup


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
}
