package io.github.group18.Model;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import io.github.group18.Model.Items.Item;

public class Refrigerator
{
    public Map<Item, Pair<Integer, Integer>> Items = new HashMap<>();
    public Map<Item, Pair<Integer, Integer>> getItems() {
        return Items;
    }
    private Stack<Integer> freeIndexes = new Stack<>();
    private int selectedSlot = -1;
    public int MaxQuantity = 9;

    public Refrigerator(int maxQuantity) {
        MaxQuantity = maxQuantity;
        for (int i = MaxQuantity - 1; i >= 0; i--) {
            freeIndexes.push(i);
        }
    }

//    public void setFreeIndexes(){
//        for (int i = 9 - 1; i >= 0; i--) {
//            freeIndexes.push(i);
//        }
//    }

//    public boolean addItem(Item item, int quantity) {
//        if (quantity <= 0) return false;
//
//        int currentQuantity = Items.getOrDefault(item, 0);
//        int newQuantity = currentQuantity + quantity;
//
//        Items.put(item, newQuantity);
//        return true;
//    }
//
//    public boolean removeItem(Item item, int quantity) {
//        if (quantity <= 0) return false;
//
//        int currentQuantity = Items.getOrDefault(item, 0);
//
//        if (currentQuantity < quantity) {
//            return false;
//        }
//
//        int newQuantity = currentQuantity - quantity;
//
//        if (newQuantity == 0) {
//            Items.remove(item);
//        } else {
//            Items.put(item, newQuantity);
//        }
//
//        return true;
//    }
    public boolean addItem(Item itemId, int quantity) {
        if (quantity <= 0 || freeIndexes.isEmpty()) {
            return false;
        }
        if (Items.containsKey(itemId)) {
            Pair<Integer, Integer> existing = Items.get(itemId);
            Items.put(itemId, new Pair<>(existing.first + quantity, existing.second));
            return true;
        } else {
            int slot = freeIndexes.pop();
            Items.put(itemId, new Pair<>(quantity, slot));
            return true;
        }
    }

    public boolean removeItem(Item itemId, int quantity) {
        if (!Items.containsKey(itemId) || quantity <= 0) return false;

        Pair<Integer, Integer> pair = Items.get(itemId);
        int currentQty = pair.first;

        if (currentQty < quantity) return false;

        int newQty = currentQty - quantity;
        if (newQty == 0) {
            freeIndexes.push(pair.second);
            Items.remove(itemId);
        } else {
            Items.put(itemId, new Pair<>(newQty, pair.second));
        }

        return true;
    }
    public int getItemQuantity(Item itemId) {
        return Items.getOrDefault(itemId, new Pair<>(0, -1)).first;
    }

    public boolean hasItem(Item item) {
        return Items.containsKey(item);
    }

    public int getUniqueItemCount() {
        return Items.size();
    }

    public int getTotalItemCount() {
        return Items.size();
    }

    public void clearInventory() {
        Items.clear();
    }

    public void adaptMap(HashMap<Integer ,Integer> LakeMap)
    {

    }

    public Stack<Integer> getFreeIndexes() {
        return freeIndexes;
    }

    public void setFreeIndexes(Stack<Integer> freeIndexes) {
        this.freeIndexes = freeIndexes;
    }

    public void setItems(Map<Item, Pair<Integer, Integer>> items) {
        Items = items;
    }

    public int getSelectedSlot() {
        return selectedSlot;
    }

    public void setSelectedSlot(int selectedSlot) {
        this.selectedSlot = selectedSlot;
    }

    public int getMaxQuantity() {
        return MaxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        MaxQuantity = maxQuantity;
    }
}
