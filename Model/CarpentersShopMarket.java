package Model;

import Model.Items.Mineral;
import enums.ForagingMineralsEnums;

import java.util.HashMap;

public class CarpentersShopMarket  implements adaptMapMarket
{
    HashMap<Object, Integer> Stock = new HashMap<>();

    public HashMap<Object, Integer> getStock() {
        return Stock;
    }

    public void setStock(HashMap<Object, Integer> stock) {
        Stock = stock;
    }

    public void addItem(Mineral mineral, int quantity) {
        Stock.put(mineral, Stock.getOrDefault(mineral, 0) + quantity);
    }

    public void removeItem(Object item, int quantity) {
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

        Cage cage = new Cage();
        ///cage
        //mineral1.setPrice(75);
        //Stock.put(mineral1, -1);

        Mineral mineral2 = new Mineral();
        mineral2.setType(ForagingMineralsEnums.Iron);
        mineral2.setPrice(150);
        Stock.put(mineral2, -1);

        Mineral mineral3 = new Mineral();
        mineral3.setType(ForagingMineralsEnums.Coal);
        mineral3.setPrice(150);
        Stock.put(mineral3, -1);

        Mineral mineral4 = new Mineral();
        mineral4.setType(ForagingMineralsEnums.Gold);
        mineral4.setPrice(400);
        Stock.put(mineral4, -1);
    }
}