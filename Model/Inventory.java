package Model;

import Model.Items.Item;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    protected int MaxQuantity;
    protected String Type;
    protected HashMap<Item, Integer> Items = new HashMap<>();

    // Constructor
    public Inventory(int maxQuantity, String type) {
        this.MaxQuantity = maxQuantity;
        this.Type = type;
    }

    // Getter and Setter for MaxQuantity
    public int getMaxQuantity() {
        return MaxQuantity;
    }

    public void setMaxQuantity(int MaxQuantity) {
        this.MaxQuantity = MaxQuantity;
    }

    // Getter and Setter for Type
    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    // Getter for Items (returns a copy for encapsulation)
    public Map<Item, Integer> getItems() {
        return Items;
    }


    public boolean addItem(Item item, int quantity) {
        if (quantity <= 0) return false;

        int currentQuantity = Items.getOrDefault(item, 0);
        int newQuantity = currentQuantity + quantity;

        if (newQuantity > MaxQuantity) {
            return false;
        }

        Items.put(item, newQuantity);
        return true;
    }

    public boolean removeItem(Item item, int quantity) {
        if (quantity <= 0) return false;

        int currentQuantity = Items.getOrDefault(item, 0);

        if (currentQuantity < quantity) {
            return false;
        }

        int newQuantity = currentQuantity - quantity;

        if (newQuantity == 0) {
            Items.remove(item);
        } else {
            Items.put(item, newQuantity);
        }

        return true;
    }


    public int getItemQuantity(Item item) {
        return Items.getOrDefault(item, 0);
    }


    public boolean hasItem(Item item) {
        return Items.containsKey(item);
    }


    public int getUniqueItemCount() {
        return Items.size();
    }


    public int getTotalItemCount() {
        return Items.values().stream().mapToInt(Integer::intValue).sum();
    }


    public boolean isFull() {
        return getTotalItemCount() >= MaxQuantity;
    }


    public void clearInventory() {
        Items.clear();
    }


}