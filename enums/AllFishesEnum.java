package enums;

public enum AllFishesEnum {
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
    RainbowTrout(65, Seasons.Summer),
    Angler(900, Seasons.Fall),

    Glacierfish(1000, Seasons.Winter),

    Crimsonfish(1500, Seasons.Spring),

    Legend(5000, Seasons.Summer);

    private final int basePrice;
    private final Seasons season;

    // سازنده برای مقداردهی به ویژگی‌ها
    AllFishesEnum(int value, Seasons season) {
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
    public static boolean isValidFish(String inputFish) {
        for (AllFishesEnum fish : AllFishesEnum.values()) {
            if(fish.name().equalsIgnoreCase(inputFish)) {
                return true;
            }
        }
        return false;
    }
    public static AllFishesEnum getEnum(String name) {
        for(AllFishesEnum allTreesEnums : AllFishesEnum.values()){
            if (allTreesEnums.name().equalsIgnoreCase(name)){
                return allTreesEnums;
            }
        }
        return null;
    }

}