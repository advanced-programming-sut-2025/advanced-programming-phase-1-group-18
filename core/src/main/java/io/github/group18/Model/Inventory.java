package io.github.group18.Model;

import io.github.group18.Model.Items.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Inventory {
    protected int MaxQuantity;
    protected String Type;

    private Map<Item, Pair<Integer, Integer>> Items;
    private Stack<Integer> freeIndexes;
    private int selectedSlot = -1;

    // Constructor
    public Inventory(int maxQuantity, String type) {
        this.Items = new HashMap<>();
        this.MaxQuantity = maxQuantity;
        this.Type = type;
        this.freeIndexes = new Stack<>();

        for (int i = MaxQuantity - 1; i >= 0; i--) {
            freeIndexes.push(i);
        }
    }

    public boolean addItem(Item itemId, int quantity) {
        if (quantity <= 0 || freeIndexes.isEmpty()) return false;

        if (Items.containsKey(itemId)||containsItem(itemId)) {
            Pair<Integer, Integer> existing = existing(itemId);
            Items.put(itemId, new Pair<>(existing.first + quantity, existing.second));
            return true;
        } else {
            int slot = freeIndexes.pop();
            Items.put(itemId, new Pair<>(quantity, slot));
            return true;
        }
    }
    public boolean containsItem(Item itemId) {
        for (Map.Entry<Item, Pair<Integer, Integer>> entry : Items.entrySet()) {
            if (entry.getKey().getCorrectName().equalsIgnoreCase(itemId.getCorrectName())) {
                return true;
            }
        }
        return false;
    }

    public Pair<Integer,Integer> existing (Item itemId) {
        for (Map.Entry<Item, Pair<Integer, Integer>> entry : Items.entrySet()) {
            if (entry.getKey().getCorrectName().equalsIgnoreCase(itemId.getCorrectName())) {
                return entry.getValue();
            }
        }
        return null;
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

    public void clearInventory() {
        Items.clear();
        freeIndexes.clear();
        for (int i = MaxQuantity - 1; i >= 0; i--) {
            freeIndexes.push(i);
        }
    }

    public Item getItemBySlot(int slotNum) {
        for (Map.Entry<Item, Pair<Integer, Integer>> entry : Items.entrySet()) {
            if (entry.getValue().second == slotNum) {
                return entry.getKey();
            }
        }
        return null;
    }


    public int getItemQuantity(Item itemId) {
        return Items.getOrDefault(itemId, new Pair<>(0, -1)).first;
    }

    public int getSlot(Item itemId) {
        return Items.getOrDefault(itemId, new Pair<>(0, -1)).second;
    }

    public boolean hasItem(Item itemId) {
        return Items.containsKey(itemId);
    }

    public boolean isFull() {
        return Items.size() >= MaxQuantity;
    }

    public int getSelectedSlot() {
        return selectedSlot;
    }

    public void setSelectedSlot(int slot) {
        this.selectedSlot = slot;
    }

    public int getMaxQuantity() {
        return MaxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        MaxQuantity = maxQuantity;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Map<Item, Pair<Integer, Integer>> getItems() {
        return Items;
    }

    public void setItems(Map<Item, Pair<Integer, Integer>> items) {
        Items = items;
    }

    public Stack<Integer> getFreeIndexes() {
        return freeIndexes;
    }

    public void setFreeIndexes(Stack<Integer> freeIndexes) {
        this.freeIndexes = freeIndexes;
    }
}
