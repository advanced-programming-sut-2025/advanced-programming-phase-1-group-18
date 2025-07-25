package io.github.group18.Model.Items;

import io.github.group18.Model.App;
import io.github.group18.Model.Cord;
import io.github.group18.Model.Kashi;
import io.github.group18.Model.PictureModel;

import java.util.HashMap;
import java.util.Map;

public class Satl implements PictureModel
{
    protected HashMap<Item, Integer> Items;


    public void adaptMap(Cord cord) {
        Kashi kashi = new Kashi();
        kashi.setShokhmZadeh(false);
        kashi.setEnterance(false);
        kashi.setInside(this);
        kashi.setWalkable(false);
        App.getCurrentGame().getMap().get(cord.getX()).set(cord.getY(), kashi);
    }

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

    @Override
    public String getPath() {
        return "Chest.png";
    }
}
