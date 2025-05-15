package enums;

public enum TreeSeedEnums {
    ApricotSapling,
    CherrySapling,
    BananaSapling,
    MangoSapling,
    OrangeSapling,
    PeachSapling,
    AppleSapling,
    PomegranateSapling,
    Acorns,
    MapleSeeds,
    PineCones,
    MahoganySeeds,
    MushroomTreeSeeds,
    MysticTreeSeeds;

    public static boolean isContain(String name){
        for(TreeSeedEnums e : TreeSeedEnums.values()){
            if(e.toString().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    public static TreeSeedEnums getEnum(String name) {
        for(TreeSeedEnums treeSeedEnums : TreeSeedEnums.values()){
            if (treeSeedEnums.name().equalsIgnoreCase(name)){
                return treeSeedEnums;
            }
        }
        return null;
    }
}
