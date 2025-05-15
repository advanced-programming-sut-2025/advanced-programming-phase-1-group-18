package enums;

import Model.AllTree;
import Model.ForagingTree;

public enum AllTreesEnums {
    ApricotTree,
    CherryTree,
    BananaTree,
    MangoTree,
    OrangeTree,
    PeachTree,
    AppleTree,
    PomegranateTree,
    OakTree,
    MapleTree,
    PineTree,
    MahoganyTree,
    MushroomTree,
    MysticTree;
    public static boolean isContain (String name){
        for (AllTreesEnums allTreesEnums : AllTreesEnums.values()){
            if (allTreesEnums.name().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    public static AllTreesEnums getEnum(String name) {
        for(AllTreesEnums allTreesEnums : AllTreesEnums.values()){
            if (allTreesEnums.name().equalsIgnoreCase(name)){
                return allTreesEnums;
            }
        }
        return null;
    }
}
