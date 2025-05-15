package enums;

public enum LegendaryFishesEnums {
    Angler(900, Seasons.Fall),

    Glacierfish(1000, Seasons.Winter),

    Crimsonfish(1500, Seasons.Spring),

    Legend(5000, Seasons.Summer);

    private final int basePrice;
    private final Seasons season;

    // سازنده برای مقداردهی به ویژگی‌ها
    LegendaryFishesEnums(int value, Seasons season) {
        this.basePrice = value;
        this.season = season;
    }

    // متدهای getter برای دسترسی به ویژگی‌ها
    public int getValue() {
        return basePrice;
    }

    public Seasons getSeason() {
        return season;
    }
    public static boolean isContain (String name){
        for (LegendaryFishesEnums allTreesEnums : LegendaryFishesEnums.values()){
            if (allTreesEnums.name().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    public static LegendaryFishesEnums getEnum(String name) {
        for(LegendaryFishesEnums allTreesEnums : LegendaryFishesEnums.values()){
            if (allTreesEnums.name().equalsIgnoreCase(name)){
                return allTreesEnums;
            }
        }
        return null;
    }
}