package enums;

import Model.DateTime;

import java.util.HashMap;

public enum ArtisanGoodsEnums {
    Honey(
            CraftingRecipesEnums.BeeHouse,
            75,
            new DateTime(0,4),
            new HashMap<>() {{ }},
            350
    ),

    Cloth(
            CraftingRecipesEnums.Loom,
            0,
            new DateTime(4,0),
            new HashMap<>() {{
                put("Wool", 1);
            }},
            470
    ),

    Cheese_Normal(
            CraftingRecipesEnums.CheesePress,
            100,
            new DateTime(3,0),
            new HashMap<>() {{
                put("Milk", 1);
            }},
            230
    ),

    Cheese_Large(
            CraftingRecipesEnums.CheesePress,
            100,
            new DateTime(3,0),
            new HashMap<>() {{
                put("LargeMilk", 1);
            }},
            345
    ),

    Mayonnaise_Normal(
            CraftingRecipesEnums.MayonnaiseMachine,
            50,
            new DateTime(3,0),
            new HashMap<>() {{
                put("Egg", 1);
            }},
            190
    ),

    Mayonnaise_Large(
            CraftingRecipesEnums.MayonnaiseMachine,
            50,
            new DateTime(3,0),
            new HashMap<>() {{
                put("LargeEgg", 1);
            }},
            237
    ),

    GoatCheese_Normal(
            CraftingRecipesEnums.CheesePress,
            100,
            new DateTime(3,0),
            new HashMap<>() {{
                put("GoatMilk", 1);
            }},
            400
    ),

    GoatCheese_Large(
            CraftingRecipesEnums.CheesePress,
            100,
            new DateTime(3,0),
            new HashMap<>() {{
                put("LargeGoatMilk", 1);
            }},
            600
    ),

    DuckMayonnaise(
            CraftingRecipesEnums.MayonnaiseMachine,
            75,
            new DateTime(3,0),
            new HashMap<>() {{
                put("DuckEgg", 1);
            }},
            375
    ),

    DinosaurMayonnaise(
            CraftingRecipesEnums.MayonnaiseMachine,
            125,
            new DateTime(3,0),
            new HashMap<>() {{
                put("DinosaurEgg", 1);
            }},
            800
    ),

    Beer(
            CraftingRecipesEnums.Keg,
            50,
            new DateTime(0,1),
            new HashMap<>() {{
                put("Wheat", 1);
            }},
            200
    ),

    Vinegar(
            CraftingRecipesEnums.Keg,
            13,
            new DateTime(10, 0),
            new HashMap<>() {{
                put("Rice", 1);
            }},
            100
    ),

    Coffee(
            CraftingRecipesEnums.Keg,
            75,
            new DateTime(2, 0),
            new HashMap<>() {{
                put("CoffeeBean", 5);
            }},
            150
    ),

    Mead(
            CraftingRecipesEnums.Keg,
            100,
            new DateTime(10, 0),  // تغییر از (0,10) به (10,0)
            new HashMap<>() {{
                put("Honey", 1);
            }},
            300
    ),

    PaleAle(
            CraftingRecipesEnums.Keg,
            50,
            new DateTime(0, 3),  // تغییر از (3,0) به (0,3)
            new HashMap<>() {{
                put("Hops", 1);
            }},
            300
    ),
    Wine(
            CraftingRecipesEnums.Keg,
            0, // Energy = 1.75 × Base Fruit Energy → محاسبه‌شونده
            new DateTime(7, 0),
            new HashMap<>() {{
                put("AnyFruit", 1);
            }},
            0 // Price = 3 × Base Fruit Price
    ),
    Juice(
            CraftingRecipesEnums.Keg,
            0, // Energy = 2 × Base Vegetable Energy
            new DateTime(4, 0),
            new HashMap<>() {{
                put("AnyVegetable", 1);
            }},
            0 // Price = 2.25 × Base Vegetable Price
    ),
    Jelly(
            CraftingRecipesEnums.PreservesJar,
            0, // Energy = 2 × Base Fruit Energy
            new DateTime(3, 0),
            new HashMap<>() {{
                put("AnyFruit", 1);
            }},
            0 // Price = 2 × Base Fruit Price + 50
    ),
    Pickles(
            CraftingRecipesEnums.PreservesJar,
            0, // Energy = 1.75 × Base Ingredient Energy
            new DateTime(0, 6),
            new HashMap<>() {{
                put("AnyVegetable", 1);
            }},
            0 // Price = 2 × Base Ingredient Price + 50
    ),
    TruffleOil(
            CraftingRecipesEnums.OilMaker,
            38,
            new DateTime(6, 0),  // تغییر از (0,6) به (6,0)
            new HashMap<>() {{
                put("Truffle", 1);
            }},
            1065
    ),

    Oil_Corn(
            CraftingRecipesEnums.OilMaker,
            13,
            new DateTime(6, 0),  // تغییر از (0,6) به (6,0)
            new HashMap<>() {{
                put("Corn", 1);
            }},
            100
    ),

    Oil_Seeds(
            CraftingRecipesEnums.OilMaker,
            13,
            new DateTime(0, 2),  // تغییر از (2,0) به (0,2)
            new HashMap<>() {{
                put("SunflowerSeeds", 1);
            }},
            100
    ),

    Oil_Sunflower(
            CraftingRecipesEnums.OilMaker,
            13,
            new DateTime(1, 0),  // تغییر از (0,1) به (1,0)
            new HashMap<>() {{
                put("Sunflower", 1);
            }},
            100
    ),



    Raisins(
            CraftingRecipesEnums.Dehydrator,
            125,
            new DateTime(0, 1),
            new HashMap<>() {{
                put("Grapes", 5);
            }},
            600
    ),

    DriedMushrooms(
            CraftingRecipesEnums.Dehydrator,
            50,
            new DateTime(0, 1),
            new HashMap<>() {{
                put("AnyMushroom", 5);
            }},
            0 // Price = 7.5 × Mushroom Base Price + 25
    ),

    DriedFruit(
            CraftingRecipesEnums.Dehydrator,
            75,
            new DateTime(0, 1),
            new HashMap<>() {{
                put("AnyFruit", 5);
            }},
            0 // Price = 7.5 × Fruit Base Price + 25
    ),

    SmokedFish(
            CraftingRecipesEnums.FishSmoker,
            0, // 1.5 × Fish Energy
            new DateTime(1, 0),
            new HashMap<>() {{
                put("AnyFish", 1);
                put("Coal", 1);
            }},
            0 // Price = 2 × Fish Price
    ),

    Coal(
            CraftingRecipesEnums.CharcoalKiln,
            0,
            new DateTime(1, 0),
            new HashMap<>() {{
                put("Wood", 10);
            }},
            50
    ),

    IronBar(
            CraftingRecipesEnums.Furnace,
            0,
            new DateTime(4, 0),
            new HashMap<>() {{
                put("IronOre", 5);
                put("Coal", 1);
            }},
            100 // Price = 10 × Ore Price
    ),
    CopperBar(
            CraftingRecipesEnums.Furnace,
            0,
            new DateTime(4, 0),
            new HashMap<>() {{
                put("CopperOre", 5);
                put("Coal", 1);
            }},
            50 // Price = 5 × Ore Price
    ),
    GoldBar(
            CraftingRecipesEnums.Furnace,
            0,
            new DateTime(5, 0),
            new HashMap<>() {{
                put("GoldOre", 5);
                put("Coal", 1);
            }},
            125 // Price = 5 × 25
    ),

    IridiumBar(
            CraftingRecipesEnums.Furnace,
            0,
            new DateTime(8, 0),
            new HashMap<>() {{
                put("IridiumOre", 5);
                put("Coal", 1);
            }},
            500 // Price = 5 × 100
    );



    private final HashMap<String, Integer> ingredients;
    private final CraftingRecipesEnums producer;
    private final int energy;
    private final int price;
    private final DateTime ProcessingTime;

    ArtisanGoodsEnums(CraftingRecipesEnums producer , int energy, DateTime ProcessingTime, HashMap<String, Integer> ingredients, int price) {
        this.energy = energy;
        this.price=price;
        this.producer=producer;
        this.ingredients = ingredients;
        this.ProcessingTime=ProcessingTime;
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }

    public CraftingRecipesEnums getProducer() {
        return producer;
    }

    public int getEnergy() {
        return energy;
    }

    public int getPrice() {
        return price;
    }

    public DateTime getProcessingTime() {
        return ProcessingTime;
    }

    public static boolean isContain (String name){
        for (ArtisanGoodsEnums allTreesEnums : ArtisanGoodsEnums.values()){
            if (allTreesEnums.name().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    public static ArtisanGoodsEnums getEnum(String name) {
        for(ArtisanGoodsEnums allTreesEnums : ArtisanGoodsEnums.values()){
            if (allTreesEnums.name().equalsIgnoreCase(name)){
                return allTreesEnums;
            }
        }
        return null;
    }
}