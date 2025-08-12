package io.github.group18.enums;

import java.util.ArrayList;
import java.util.List;

public enum CommonFishesEnums {
    Salmon(75, Seasons.Fall,"fish/Salmon.png"),
    Sardine(40, Seasons.Fall,"fish/Sardine.png"),
    Shad(60, Seasons.Fall,null),
    BlueDiscus(120, Seasons.Fall,null),

    MidnightCarp(150, Seasons.Winter,null),
    Squid(80, Seasons.Winter,null),
    Tuna(100, Seasons.Winter,"fish/Tuna.png"),
    Perch(55, Seasons.Winter,null),

    Flounder(100, Seasons.Spring,null),
    Lionfish(100, Seasons.Spring,"fish/Lionfish.png"),
    Herring(30, Seasons.Spring,null),
    Ghostfish(45, Seasons.Spring,null),

    Tilapia(75, Seasons.Summer,null),
    Dorado(100, Seasons.Summer,null),
    Sunfish(30, Seasons.Summer,null),
    RainbowTrout(65, Seasons.Summer,null);

    private final int basePrice;
    private final Seasons season;
    private final String imagePath;

    CommonFishesEnums(int value, Seasons season,String imagePath) {
        this.basePrice = value;
        this.season = season;
        this.imagePath = imagePath;
    }


    public int getValue() {
        return basePrice;
    }

    public Seasons getSeason() {
        return season;
    }

    public String getImagePath() {
        return imagePath;
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

    public static List<CommonFishesEnums> getFishesWithImage() {
        List<CommonFishesEnums> list = new ArrayList<>();
        for (CommonFishesEnums fish : CommonFishesEnums.values()) {
            if (fish.getImagePath() != null && !fish.getImagePath().isEmpty()) {
                list.add(fish);
            }
        }
        return list;
    }
}
