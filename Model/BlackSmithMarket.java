package Model;

import Model.Items.Mineral;
import enums.ForagingMineralsEnums;

import java.util.HashMap;

public class BlackSmithMarket implements adaptMapMarket {
    HashMap<Mineral, Integer> Stock = new HashMap<>();

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
        if (Stock.size() != 0) {
            Stock.clear();
        }
        Stock = new HashMap<>();

        Mineral mineral1 = new Mineral();
        mineral1.setType(ForagingMineralsEnums.CopperOre);
        mineral1.setPrice(75);
        Stock.put(mineral1, Integer.MAX_VALUE);

        Mineral mineral2 = new Mineral();
        mineral2.setType(ForagingMineralsEnums.IronOre);
        mineral2.setPrice(150);
        Stock.put(mineral2, Integer.MAX_VALUE);

        Mineral mineral3 = new Mineral();
        mineral3.setType(ForagingMineralsEnums.Coal);
        mineral3.setPrice(150);
        Stock.put(mineral3, Integer.MAX_VALUE);

        Mineral mineral4 = new Mineral();
        mineral4.setType(ForagingMineralsEnums.GoldOre);
        mineral4.setPrice(400);
        Stock.put(mineral4, Integer.MAX_VALUE);
    }
}

