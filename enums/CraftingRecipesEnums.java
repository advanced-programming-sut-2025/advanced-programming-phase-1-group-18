package enums;

import java.util.HashMap;

public enum CraftingRecipesEnums {
    CherryBomb(
            new HashMap<>() {{
                put("copper ore", 4);
                put("coal", 1);
            }},
            SkillEnum.MiningSkill,
            1,
            50
    ),

    Bomb(
            new HashMap<>() {{
                put("iron ore", 4);
                put("coal", 1);
            }},
            SkillEnum.MiningSkill,
            2,
            50
    ),

    MegaBomb(
            new HashMap<>() {{
                put("gold ore", 4);
                put("coal", 1);
            }},
            SkillEnum.MiningSkill,
            3,
            50
    ),

    QualitySprinkler(
            new HashMap<>() {{
                put("iron bar", 1);
                put("gold bar", 1);
            }},
            SkillEnum.FarmingSkill,
            2,
            0
    ),

    IridiumSprinkler(
            new HashMap<>() {{
                put("gold bar", 1);
                put("iridium bar", 1);
            }},
            SkillEnum.FarmingSkill,
            3,
            0
    ),

    CharcoalKiln(
            new HashMap<>() {{
                put("wood", 20);
                put("copper bar", 2);
            }},
            SkillEnum.ForagingSkill,
            1,
            0
    ),

    Furnace(
            new HashMap<>() {{
                put("copper ore", 20);
                put("stone", 25);
            }},
            null,
            0,
            0
    ),

    Scarecrow(
            new HashMap<>() {{
                put("wood", 50);
                put("coal", 1);
                put("fiber", 20);
            }},
            null,
            0,
            0
    ),

    DeluxeScarecrow(
            new HashMap<>() {{
                put("wood", 50);
                put("coal", 1);
                put("fiber", 20);
                put("iridium ore", 1);
            }},
            SkillEnum.FarmingSkill,
            2,
            0
    ),

    BeeHouse(
            new HashMap<>() {{
                put("wood", 40);
                put("coal", 8);
                put("iron bar", 1);
            }},
            SkillEnum.FarmingSkill,
            1,
            0
    ),

    CheesePress(
            new HashMap<>() {{
                put("wood", 45);
                put("stone", 45);
                put("copper bar", 1);
            }},
            SkillEnum.FarmingSkill,
            2,
            0
    ),

    Keg(
            new HashMap<>() {{
                put("wood", 30);
                put("copper bar", 1);
                put("iron bar", 1);
            }},
            SkillEnum.FarmingSkill,
            3,
            0
    ),

    Loom(
            new HashMap<>() {{
                put("wood", 60);
                put("fiber", 30);
            }},
            SkillEnum.FarmingSkill,
            3,
            0
    ),

    MayonnaiseMachine(
            new HashMap<>() {{
                put("wood", 15);
                put("stone", 15);
                put("copper bar", 1);
            }},
            null,
            0,
            0
    ),

    OilMaker(
            new HashMap<>() {{
                put("wood", 100);
                put("gold bar", 1);
                put("iron bar", 1);
            }},
            SkillEnum.FarmingSkill,
            3,
            0
    ),

    PreservesJar(
            new HashMap<>() {{
                put("wood", 50);
                put("stone", 40);
                put("coal", 8);
            }},
            SkillEnum.FarmingSkill,
            2,
            0
    ),

    Dehydrator(
            new HashMap<>() {{
                put("wood", 30);
                put("stone", 20);
                put("fiber", 30);
            }},
            null,
            0,
            0
    ),

    GrassStarter(
            new HashMap<>() {{
                put("wood", 1);
                put("fiber", 1);
            }},
            null,
            0,
            0
    ),

    FishSmoker(
            new HashMap<>() {{
                put("wood", 50);
                put("iron bar", 3);
                put("coal", 10);
            }},
            null,
            0,
            0
    ),

    MysticTreeSeed(
            new HashMap<>() {{
                put("acorn", 5);
                put("maple seed", 5);
                put("pine cone", 5);
                put("mahogany seed", 5);
            }},
            SkillEnum.ForagingSkill,
            4,
            100
    );
    private final HashMap<String, Integer> ingredients;
    private final SkillEnum skill;
    private final int level;
    private final int price;

    CraftingRecipesEnums(HashMap<String, Integer> ingredients, SkillEnum skill, int level, int price) {
        this.level = level;
        this.price = price;
        this.skill = skill;
        this.ingredients = ingredients;
    }

    public static boolean containsCraft(String itemName) {
        itemName = itemName.toLowerCase().replace(" ", "");
        for (CraftingRecipesEnums recipe : CraftingRecipesEnums.values()) {
            if(recipe.name().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }

    public SkillEnum getSkill() {
        return skill;
    }

    public int getLevel() {
        return level;
    }

    public int getPrice() {
        return price;
    }
}
