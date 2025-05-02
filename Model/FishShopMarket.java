package Model;

import Model.Items.Food;
import Model.Items.Item;
import enums.Seasons;

import java.util.HashMap;

public class FishShopMarket  implements adaptMapMarket{
    HashMap<Item, Integer> Stock;

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
        Stock.clear();
        Stock = new HashMap<>();

        Craftingrecipe recipe = new Craftingrecipe();
        //TODO

        //fishing poles
        //TODO

    }
}
