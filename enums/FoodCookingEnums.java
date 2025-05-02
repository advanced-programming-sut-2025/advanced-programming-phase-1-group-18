package enums;

import Model.Items.Food;
import java.util.HashMap;
import java.util.Map;

public enum FoodCookingEnums {
    Friedegg(
            new HashMap<>() {{ put(createFood("egg"), 1); }}
    ),
    BakedFish(
            new HashMap<>() {{
                put(createFood("Sardine"), 1);
                put(createFood("Salmon"), 1);
                put(createFood("wheat"), 1);
            }}
    ),
    Salad(
            new HashMap<>() {{
                put(createFood("leek"), 1);
                put(createFood("dandelion"), 1);
            }}
    ),
    Omelet(
            new HashMap<>() {{
                put(createFood("egg"), 1);
                put(createFood("milk"), 1);
            }}
    ),
    pumpkinpie(
            new HashMap<>() {{
                put(createFood("pumpking"), 1);
                put(createFood("wheat flour"), 1);
                put(createFood("milk"), 1);
                put(createFood("sugar"), 1);
            }}
    ),
    spaghetti(
            new HashMap<>() {{
                put(createFood("wheat flour"), 1);
                put(createFood("tomato"), 1);
            }}
    ),
    pizza(
            new HashMap<>() {{
                put(createFood("wheat flour"), 1);
                put(createFood("tomato"), 1);
                put(createFood("cheese"), 1);
            }}
    ),
    Tortilla(
            new HashMap<>() {{ put(createFood("corn"), 1); }}
    ),
    MakiRoll(
            new HashMap<>() {{
                put(createFood("any fish"), 1);
                put(createFood("rice"), 1);
                put(createFood("fiber"), 1);
            }}
    ),
    TripleShotEspresso(
            new HashMap<>() {{ put(createFood("coffee"), 3); }}
    ),
    Cookie(
            new HashMap<>() {{
                put(createFood("wheat flour"), 1);
                put(createFood("sugar"), 1);
                put(createFood("egg"), 1);
            }}
    ),
    hashbrowns(
            new HashMap<>() {{
                put(createFood("potato"), 1);
                put(createFood("oil"), 1);
            }}
    ),
    pancakes(
            new HashMap<>() {{
                put(createFood("wheat flour"), 1);
                put(createFood("egg"), 1);
            }}
    ),
    fruitsalad(
            new HashMap<>() {{
                put(createFood("blueberry"), 1);
                put(createFood("melon"), 1);
                put(createFood("apricot"), 1);
            }}
    ),
    redplate(
            new HashMap<>() {{
                put(createFood("red cabbage"), 1);
                put(createFood("radish"), 1);
            }}
    ),
    bread(
            new HashMap<>() {{ put(createFood("wheat flour"), 1); }}
    ),
    salmondinner(
            new HashMap<>() {{
                put(createFood("salmon"), 1);
                put(createFood("Amaranth"), 1);
                put(createFood("Kale"), 1);
            }}
    ),
    vegetablemedley(
            new HashMap<>() {{
                put(createFood("tomato"), 1);
                put(createFood("beet"), 1);
            }}
    ),
    farmerslunch(
            new HashMap<>() {{
                put(createFood("omelet"), 1);
                put(createFood("parsnip"), 1);
            }}
    ),
    survivalburger(
            new HashMap<>() {{
                put(createFood("bread"), 1);
                put(createFood("carrot"), 1);
                put(createFood("eggplant"), 1);
            }}
    ),
    dishOtheSea(
            new HashMap<>() {{
                put(createFood("sardines"), 2);
                put(createFood("hash browns"), 1);
            }}
    ),
    seaformPudding(
            new HashMap<>() {{
                put(createFood("Flounder"), 1);
                put(createFood("midnight carp"), 1);
            }}
    ),
    minerstreat(
            new HashMap<>() {{
                put(createFood("carrot"), 2);
                put(createFood("sugar"), 1);
                put(createFood("milk"), 1);
            }}
    );

    private final HashMap<Food, Integer> ingredients;

    FoodCookingEnums(HashMap<Food, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public HashMap<Food, Integer> getIngredients() {
        return ingredients;
    }

    private static Food createFood(String name) {
        Food food = new Food();
        food.setName(name);
        return food;
    }
}