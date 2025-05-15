package Model;

import Model.Items.Mineral;
import Model.Items.StoneItem;
import enums.ForagingMineralsEnums;

import java.util.HashMap;

public class CarpentersShopMarket implements adaptMapMarket {
    HashMap<Object, Integer> Stock = new HashMap<>();
    private boolean barn = false;
    private boolean bigbarn = false;
    private boolean deluxebarn = false;
    private boolean coop = false;
    private boolean bigcoop = false;
    private boolean deluxecoop = false;

    public boolean isBarn() {
        return barn;
    }

    public void setBarn(boolean barn) {
        this.barn = barn;
    }

    public boolean isDeluxecoop() {
        return deluxecoop;
    }

    public void setDeluxecoop(boolean deluxecoop) {
        this.deluxecoop = deluxecoop;
    }

    public boolean isBigcoop() {
        return bigcoop;
    }

    public void setBigcoop(boolean bigcoop) {
        this.bigcoop = bigcoop;
    }

    public boolean isDeluxebarn() {
        return deluxebarn;
    }

    public void setDeluxebarn(boolean deluxebarn) {
        this.deluxebarn = deluxebarn;
    }

    public boolean isCoop() {
        return coop;
    }

    public void setCoop(boolean coop) {
        this.coop = coop;
    }

    public boolean isBigbarn() {
        return bigbarn;
    }

    public void setBigbarn(boolean bigbarn) {
        this.bigbarn = bigbarn;
    }

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
        Stock.put(cage, 1);
        BigCoop bigCoop = new BigCoop();
        Stock.put(bigCoop, 1);
        DeluxeCoop deluxecoop = new DeluxeCoop();
        Stock.put(deluxecoop, 1);
        Tavileh tavileh = new Tavileh();
        Stock.put(tavileh, 1);
        BigBarn bigBarn = new BigBarn();
        Stock.put(bigBarn, 1);
        DeluxeBarn deluxebarn = new DeluxeBarn();
        Stock.put(deluxebarn, 1);
        StoneItem stoneItem = new StoneItem();
        stoneItem.setPrice(10);
        Stock.put(stoneItem, Integer.MAX_VALUE);
        ShippingBin shippingBin = new ShippingBin();
        Stock.put(shippingBin, Integer.MAX_VALUE);
        Well well = new Well();
        Stock.put(well, 1);
    }
}