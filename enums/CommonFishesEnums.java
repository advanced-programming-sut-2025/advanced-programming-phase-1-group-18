package enums;

public enum CommonFishesEnums {
    Salmon(75, Seasons.Fall),
    Sardine(40, Seasons.Fall),
    Shad(60, Seasons.Fall),
    BlueDiscus(120, Seasons.Fall),

    MidnightCarp(150, Seasons.Winter),
    Squid(80, Seasons.Winter),
    Tuna(100, Seasons.Winter),
    Perch(55, Seasons.Winter),

    Flounder(100, Seasons.Spring),
    Lionfish(100, Seasons.Spring),
    Herring(30, Seasons.Spring),
    Ghostfish(45, Seasons.Spring),

    Tilapia(75, Seasons.Summer),
    Dorado(100, Seasons.Summer),
    Sunfish(30, Seasons.Summer),
    RainbowTrout(65, Seasons.Summer);

    private final int basePrice;
    private final Seasons season;

    // سازنده برای مقداردهی به ویژگی‌ها
    CommonFishesEnums(int value, Seasons season) {
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
        for (CommonFishesEnums allTreesEnums : CommonFishesEnums.values()){
            if (allTreesEnums.name().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    public static CommonFishesEnums getEnum(String name) {
        for(CommonFishesEnums allTreesEnums : CommonFishesEnums.values()){
            if (allTreesEnums.name().equalsIgnoreCase(name)){
                return allTreesEnums;
            }
        }
        return null;
    }
}