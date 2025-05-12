package Model;

import Model.Items.Food;
import Model.Items.FoodCooking;
import Model.Items.Hay;
import Model.Items.Item;
import enums.FoodCookingEnums;

import java.util.HashMap;

public class TheStardropSaloonMarket  implements adaptMapMarket{
    HashMap<Object, Integer> Stock = new HashMap<>();

    public HashMap<Object, Integer> getStock() {
        return Stock;
    }

    public void setStock(HashMap<Object, Integer> stock) {
        Stock = stock;
    }

    public void addItem(Object item, int quantity) {
        Stock.put(item, Stock.getOrDefault(item, 0) + quantity);
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
        if (Stock.size() != 0) {
            Stock.clear();
        }
        Stock = new HashMap<>();

        Food food = new Food();
        food.setName("Beer");
        food.setPrice(400);
        Stock.put(food, Integer.MAX_VALUE);

        FoodCooking foodCooking = new FoodCooking();
        foodCooking.setName(FoodCookingEnums.Salad);
        foodCooking.setSellPrice(220);
        Stock.put(foodCooking, Integer.MAX_VALUE);

        FoodCooking bread = new FoodCooking();
        bread.setName(FoodCookingEnums.bread);
        bread.setSellPrice(120);
        Stock.put(bread, Integer.MAX_VALUE);

        FoodCooking spaghetti = new FoodCooking();
        spaghetti.setName(FoodCookingEnums.spaghetti);
        spaghetti.setSellPrice(240);
        Stock.put(spaghetti, Integer.MAX_VALUE);

        FoodCooking pizza = new FoodCooking();
        pizza.setName(FoodCookingEnums.pizza);
        pizza.setSellPrice(600);
        Stock.put(pizza, Integer.MAX_VALUE);

        Food food1 = new Food();
        food1.setName("coffee");
        food1.setPrice(300);
        Stock.put(food1, Integer.MAX_VALUE);

        Cookingrecipe hashbrowns = new Cookingrecipe();
        hashbrowns.setFood(FoodCookingEnums.hashbrowns);
        hashbrowns.setPrice(50);
        Stock.put(hashbrowns,1);

        Cookingrecipe omelet = new Cookingrecipe();
        omelet.setFood(FoodCookingEnums.Omelet);
        omelet.setPrice(100);
        Stock.put(omelet, 1);

        Cookingrecipe pancakes = new Cookingrecipe();
        pancakes.setFood(FoodCookingEnums.pancakes);
        pancakes.setPrice(100);
        Stock.put(pancakes, 1);

        Cookingrecipe bread1 = new Cookingrecipe();
        bread1.setFood(FoodCookingEnums.bread);
        bread1.setPrice(100);
        Stock.put(bread1, 1);

        Cookingrecipe tortilla = new Cookingrecipe();
        tortilla.setFood(FoodCookingEnums.Tortilla);
        tortilla.setPrice(100);
        Stock.put(tortilla, 1);

        Cookingrecipe pizza1 = new Cookingrecipe();
        pizza1.setFood(FoodCookingEnums.pizza);
        pizza1.setPrice(150);
        Stock.put(pizza1, 1);

        Cookingrecipe makiRoll = new Cookingrecipe();
        makiRoll.setFood(FoodCookingEnums.MakiRoll);
        makiRoll.setPrice(300);
        Stock.put(makiRoll, 1);

        Cookingrecipe tripleShotEspresso = new Cookingrecipe();
        tripleShotEspresso.setFood(FoodCookingEnums.TripleShotEspresso);
        tripleShotEspresso.setPrice(5000);
        Stock.put(tripleShotEspresso, 1);

        Cookingrecipe cookie = new Cookingrecipe();
        cookie.setFood(FoodCookingEnums.Cookie);
        cookie.setPrice(300);
        Stock.put(cookie, 1);

    }
}
