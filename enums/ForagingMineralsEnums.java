package enums;

public enum ForagingMineralsEnums
{
    Quartz,
    EarthCrystal,
    FrozenTear,
    FireQuartz,
    Emerald,
    Aquamarine,
    Ruby,
    Amethyst,
    Topaz,
    Jade,
    Diamond,
    PrismaticShard,
    CopperOre,
    IronOre,
    GoldOre,
    IridiumOre,
    Coal;

    public static boolean isContain(String name){
        for(ForagingMineralsEnums enums : values()){
            if(enums.name().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    public static ForagingMineralsEnums getEnum(String name) {
        for(ForagingMineralsEnums foragingMineralsEnums : ForagingMineralsEnums.values()){
            if (foragingMineralsEnums.name().equalsIgnoreCase(name)){
                return foragingMineralsEnums;
            }
        }
        return null;
    }
}
