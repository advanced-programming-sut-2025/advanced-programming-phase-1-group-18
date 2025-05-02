package Model;

import Model.Items.Mineral;
import enums.ForagingMineralsEnums;

import java.util.ArrayList;
import java.util.HashMap;

public class BlackSmithMarket implements adaptMapMarket{
    HashMap<Mineral, Integer> Stock;

    public HashMap<Mineral, Integer> getStock() {
        return Stock;
    }

    public void setStock(HashMap<Mineral, Integer> stock) {
        Stock = stock;
    }

    public void addItem(Mineral mineral, int quantity) {
        Stock.put(mineral, Stock.getOrDefault(mineral, 0) + quantity);
    }

    public void removeItem(Mineral mineral, int quantity) {
        if (Stock.containsKey(mineral)) {
            int currentQuantity = Stock.get(mineral);
            if (currentQuantity > quantity) {
                Stock.put(mineral, currentQuantity - quantity);
            } else {
                Stock.remove(mineral);
            }
        }
    }

    public void fillStock() {
        Stock.clear();
        Stock = new HashMap<>();

        Mineral mineral1 = new Mineral();
        mineral1.setType(ForagingMineralsEnums.Copper);
        mineral1.setPrice(75);
        Stock.put(mineral1, -1);

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

