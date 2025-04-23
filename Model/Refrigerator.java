package Model;
import java.util.ArrayList;
import java.util.HashMap;

import Model.Items.*;
public class Refrigerator
{
    protected  ArrayList<Item> FoodCookings;

    public HashMap<Item, Integer> getMohtavyaat() {
        return Mohtavyaat;
    }

    public void setMohtavyaat(HashMap<Item, Integer> mohtavyaat) {
        this.Mohtavyaat = mohtavyaat;
    }

    public ArrayList<Item> getFoodCookings() {
        return FoodCookings;
    }

    public void setFoodCookings(ArrayList<Item> foodCookings) {
        this.FoodCookings = foodCookings;
    }

    protected   HashMap<Item,Integer> Mohtavyaat = new HashMap<>();
    public void adaptMap(HashMap<Integer ,Integer> LakeMap)
    {

    }
}