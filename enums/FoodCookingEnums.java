package enums;

import Model.Items.Food;

import java.util.HashMap;
import java.util.Map;

public enum FoodCookingEnums {

    Friedegg(
            new HashMap<>() {{
                put("egg", 1);
            }}
    ),
    BakedFish(
            new HashMap<>() {{
                put("Sardine", 1);
                put("Salmon", 1);
                put("wheat", 1);
            }}
    ),
    Salad(
            new HashMap<>() {{
                put("leek", 1);
                put("dandelion", 1);
            }}
    ),
    Omelet(
            new HashMap<>() {{
                put("egg", 1);
                put("milk", 1);
            }}
    ),
    PumpkinPie(
            new HashMap<>() {{
                put("pumpkin", 1);
                put("wheat", 1);
                put("milk", 1);
                put("sugar", 1);
            }}
    ),
    Spaghetti(
            new HashMap<>() {{
                put("wheat", 1);
                put("tomato", 1);
            }}
    ),
    Pizza(
            new HashMap<>() {{
                put("wheat", 1);
                put("tomato", 1);
                put("cheese", 1);
            }}
    ),
    Tortilla(
            new HashMap<>() {{
                put("corn", 1);
            }}
    ),
    MakiRoll(
            new HashMap<>() {{
                put("any fish", 1);
                put("rice", 1);
                put("fiber", 1);
            }}
    ),
    TripleShotEspresso(
            new HashMap<>() {{
                put("coffee", 3);
            }}
    ),
    Cookie(
            new HashMap<>() {{
                put("wheat", 1);
                put("sugar", 1);
                put("egg", 1);
            }}
    ),
    HashBrowns(
            new HashMap<>() {{
                put("potato", 1);
                put("oil", 1);
            }}
    ),
    Pancakes(
            new HashMap<>() {{
                put("wheat", 1);
                put("egg", 1);
            }}
    ),
    FruitSalad(
            new HashMap<>() {{
                put("blueberry", 1);
                put("melon", 1);
                put("apricot", 1);
            }}
    ),
    RedPlate(
            new HashMap<>() {{
                put("red cabbage", 1);
                put("radish", 1);
            }}
    ),
    Bread(
            new HashMap<>() {{
                put("wheat", 1);
            }}
    ),
    SalmonDinner(
            new HashMap<>() {{
                put("salmon", 1);
                put("Amaranth", 1);
                put("Kale", 1);
            }}
    ),
    VegetableMedley(
            new HashMap<>() {{
                put("tomato", 1);
                put("beet", 1);
            }}
    ),
    FarmersLunch(
            new HashMap<>() {{
                put("omelet", 1);
                put("parsnip", 1);
            }}
    ),
    SurvivalBurger(
            new HashMap<>() {{
                put("bread", 1);
                put("carrot", 1);
                put("eggplant", 1);
            }}
    ),
    DishOTheSea(
            new HashMap<>() {{
                put("sardines", 2);
                put("hash browns", 1);
            }}
    ),
    SeaformPudding(
            new HashMap<>() {{
                put("Flounder", 1);
                put("midnight carp", 1);
            }}
    ),
    MinersTreat(
            new HashMap<>() {{
                put("carrot", 2);
                put("sugar", 1);
                put("milk", 1);
            }}
    );


    private final HashMap<String, Integer> ingredients;

    FoodCookingEnums(HashMap<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }

    private static Food createFood(String name) {
        Food food = new Food();
        food.setName(name);
        return food;
    }
}