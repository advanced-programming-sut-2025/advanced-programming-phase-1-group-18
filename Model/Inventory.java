package Model;
import java.util.ArrayList;
import java.util.HashMap;

import Model.Items.Item;
public class Inventory
{
    protected int MaxQuantity;
    protected  String Type;
    protected HashMap<Item,Integer> Items = new HashMap<>();

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

    // Getter and Setter for Items
    public HashMap<Item, Integer> getItems() {
        return Items;
    }

    public void setItems(HashMap<Item, Integer> Items) {
        this.Items = Items;
    }


}
