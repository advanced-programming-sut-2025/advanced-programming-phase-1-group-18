package enums;

public enum ForagingCropsEnums
{

    CommonMushroom,
    Daffodil,
    Dandelion,
    Leek,
    Morel,
    Salmonberry,
    SpringOnion,
    WildHorseradish,
    FiddleheadFern,
    Grape,
    RedMushroom,
    SpiceBerry,
    SweetPea,
    Blackberry,
    Chanterelle,
    Hazelnut,
    PurpleMushroom,
    WildPlum,
    Crocus,
    CrystalFruit,
    Holly,
    SnowYam,
    WinterRoot;
    public static boolean isContain (String name){
        for (ForagingCropsEnums allTreesEnums : ForagingCropsEnums.values()){
            if (allTreesEnums.name().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    public static ForagingCropsEnums getEnum(String name) {
        for(ForagingCropsEnums allTreesEnums : ForagingCropsEnums.values()){
            if (allTreesEnums.name().equalsIgnoreCase(name)){
                return allTreesEnums;
            }
        }
        return null;
    }
}
