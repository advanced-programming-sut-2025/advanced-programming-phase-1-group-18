package Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Model.Items.*;
public class Refrigerator
{
    protected HashMap<Item, Integer> Items = new HashMap<>();
    public Map<Item, Integer> getItems() {
        return Items;
    }

    public boolean addItem(Item item, int quantity) {
        if (quantity <= 0) return false;

        int currentQuantity = Items.getOrDefault(item, 0);
        int newQuantity = currentQuantity + quantity;

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

    public void clearInventory() {
        Items.clear();
    }

    public void adaptMap(HashMap<Integer ,Integer> LakeMap)
    {

    }
}