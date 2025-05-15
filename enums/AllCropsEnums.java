package enums;

public enum AllCropsEnums {
    BlueJazz(ForagingSeedsEnums.JazzSeeds),
    Carrot(ForagingSeedsEnums.CarrotSeeds),
    CauliFlower(ForagingSeedsEnums.CauliflowerSeeds),
    CoffeeBean(ForagingSeedsEnums.CoffeeBean),
    Garlic(ForagingSeedsEnums.GarlicSeeds),
    GreenBean(ForagingSeedsEnums.BeanStarter),
    Kale(ForagingSeedsEnums.KaleSeeds),
    Parsnip(ForagingSeedsEnums.ParsnipSeeds),
    Potato(ForagingSeedsEnums.PotatoSeeds),
    Rhubarb(ForagingSeedsEnums.RhubarbSeeds),
    StrawBerry(ForagingSeedsEnums.StrawberrySeeds),
    Tulip(ForagingSeedsEnums.TulipBulb),
    UnmilledRice(ForagingSeedsEnums.RiceShoot),
    BlueBerry(ForagingSeedsEnums.BlueberrySeeds),
    Corn(ForagingSeedsEnums.CornSeeds),
    Hops(ForagingSeedsEnums.HopsStarter),
    HotPepper(ForagingSeedsEnums.PepperSeeds),
    Melon(ForagingSeedsEnums.MelonSeeds),
    Poppy(ForagingSeedsEnums.PoppySeeds),
    Radish(ForagingSeedsEnums.RadishSeeds),
    RedCabbage(ForagingSeedsEnums.RedCabbageSeeds),
    StarFruit(ForagingSeedsEnums.StarfruitSeeds),
    SummerSpangle(ForagingSeedsEnums.SpangleSeeds),
    SummerSquash(ForagingSeedsEnums.SummerSquashSeeds),
    SunFlower(ForagingSeedsEnums.SunflowerSeeds),
    Tomato(ForagingSeedsEnums.TomatoSeeds),
    Wheat(ForagingSeedsEnums.WheatSeeds),
    Amaranth(ForagingSeedsEnums.AmaranthSeeds),
    Artichoke(ForagingSeedsEnums.ArtichokeSeeds),
    Beet(ForagingSeedsEnums.BeetSeeds),
    Bokchoy(ForagingSeedsEnums.BokChoySeeds),
    Broccoli(ForagingSeedsEnums.BroccoliSeeds),
    CramBerries(ForagingSeedsEnums.CranberrySeeds),
    EggPlant(ForagingSeedsEnums.EggplantSeeds),
    FairyRose(ForagingSeedsEnums.FairySeeds),
    Grape(ForagingSeedsEnums.GrapeStarter),
    Pumpkin(ForagingSeedsEnums.PumpkinSeeds),
    Yam(ForagingSeedsEnums.YamSeeds),
    SweetGemBerry(ForagingSeedsEnums.RareSeed),
    PowderMelon(ForagingSeedsEnums.PowdermelonSeeds),
    AncientFruit(ForagingSeedsEnums.AncientSeeds);


    private final ForagingSeedsEnums seedSet;

    AllCropsEnums(ForagingSeedsEnums seedSet) {
        this.seedSet = seedSet;
    }

    public ForagingSeedsEnums getSeedSet() {
        return seedSet;
    }

    public static boolean isContain (String name){
        for (AllCropsEnums allTreesEnums : AllCropsEnums.values()){
            if (allTreesEnums.name().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    public static AllCropsEnums getEnum(String name) {
        for(AllCropsEnums allTreesEnums : AllCropsEnums.values()){
            if (allTreesEnums.name().equalsIgnoreCase(name)){
                return allTreesEnums;
            }
        }
        return null;
    }
}
