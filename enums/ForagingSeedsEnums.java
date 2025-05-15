package enums;

public enum ForagingSeedsEnums
{
    JazzSeeds,
    CarrotSeeds,
    CauliflowerSeeds,
    CoffeeBean,
    GarlicSeeds,
    BeanStarter,
    KaleSeeds,
    ParsnipSeeds,
    PotatoSeeds,
    RhubarbSeeds,
    StrawberrySeeds,
    TulipBulb,
    RiceShoot,
    BlueberrySeeds,
    CornSeeds,
    HopsStarter,
    PepperSeeds,
    MelonSeeds,
    PoppySeeds,
    RadishSeeds,
    RedCabbageSeeds,
    StarfruitSeeds,
    SpangleSeeds,
    SummerSquashSeeds,
    SunflowerSeeds,
    TomatoSeeds,
    WheatSeeds,
    AmaranthSeeds,
    ArtichokeSeeds,
    BeetSeeds,
    BokChoySeeds,
    BroccoliSeeds,
    CranberrySeeds,
    EggplantSeeds,
    FairySeeds,
    GrapeStarter,
    PumpkinSeeds,
    YamSeeds,
    RareSeed,
    PowdermelonSeeds,
    AncientSeeds,
    MixedSeeds;

    ForagingSeedsEnums() {
    }

    public static boolean isContain (String name){
        for (ForagingSeedsEnums foragingSeedsEnum : ForagingSeedsEnums.values()){
            if (foragingSeedsEnum.name().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    public static ForagingSeedsEnums getEnum(String name) {
        for(ForagingSeedsEnums foragingSeedsEnum : ForagingSeedsEnums.values()){
            if (foragingSeedsEnum.name().equalsIgnoreCase(name)){
                return foragingSeedsEnum;
            }
        }
        return null;
    }
}
