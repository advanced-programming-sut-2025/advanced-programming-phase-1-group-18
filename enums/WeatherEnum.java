package enums;

import java.util.EnumSet;
import java.util.Set;

public enum WeatherEnum {
    SUNNY(EnumSet.of(Seasons.Spring, Seasons.Summer, Seasons.Fall, Seasons.Winter)),
    RAIN(EnumSet.of(Seasons.Spring, Seasons.Summer, Seasons.Fall)),
    STORM(EnumSet.of(Seasons.Spring, Seasons.Summer, Seasons.Fall)),
    SNOW(EnumSet.of(Seasons.Winter));

    private final Set<Seasons> allowedSeasons;

    WeatherEnum(Set<Seasons> allowedSeasonss) {
        this.allowedSeasons = allowedSeasonss;
    }

    public boolean isAllowedIn(Seasons Seasons) {
        return allowedSeasons.contains(Seasons);
    }
}