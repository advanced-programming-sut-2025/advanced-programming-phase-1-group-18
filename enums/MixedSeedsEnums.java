package enums;

import java.util.EnumSet;
import java.util.Set;

public enum MixedSeedsEnums {
    //FALL
    Artichoke(EnumSet.of(Seasons.Fall)),
    Corn(EnumSet.of(Seasons.Fall, Seasons.Summer)),
    Eggplant(EnumSet.of(Seasons.Fall)),
    Pumpkin(EnumSet.of(Seasons.Fall)),
    Sunflower(EnumSet.of(Seasons.Fall,Seasons.Summer)),
    FairyRose(EnumSet.of(Seasons.Fall)),
    //SPRING
    CauliFlower(EnumSet.of(Seasons.Spring)),
    Parsnip(EnumSet.of(Seasons.Spring)),
    Potato(EnumSet.of(Seasons.Spring)),
    BlueJazz(EnumSet.of(Seasons.Spring)),
    Tulip(EnumSet.of(Seasons.Spring)),
    //SUMMER
    HotPepper(EnumSet.of(Seasons.Summer)),
    Radish(EnumSet.of(Seasons.Summer)),
    Wheat(EnumSet.of(Seasons.Summer)),
    Poppy(EnumSet.of(Seasons.Summer)),
    SummerSpangle(EnumSet.of(Seasons.Summer)),
    //WINTER
    PowderMelon(EnumSet.of(Seasons.Winter));

    private Set<Seasons> allowedSeasons;

    MixedSeedsEnums(EnumSet<Seasons> allowedSeasons) {
        this.allowedSeasons = allowedSeasons;
    }

    public boolean isAllowedIn(Seasons Seasons) {
        return allowedSeasons.contains(Seasons);
    }
    public static boolean isContain(String name){
        for(MixedSeedsEnums enums : values()){
            if(enums.name().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    public static MixedSeedsEnums getEnum(String name) {
        for(MixedSeedsEnums mixedSeedsEnums : MixedSeedsEnums.values()){
            if (mixedSeedsEnums.name().equalsIgnoreCase(name)){
                return mixedSeedsEnums;
            }
        }
        return null;
    }
}
