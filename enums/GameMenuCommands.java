package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    ShowCurrentMenu("\\s*show\\s+current\\s+menu\\s*"),
    GameNew("\\s*game\\s+new\\s+-u\\s+\\w+(\\s+\\w+)?(\\s+\\w+)?"),
    ExitGame("\\s*exit\\s+game\\s*"),
    VoteTerminateGame("\\s*vote\\s+terminate\\s+game\\s*"),
    NextTurn("\\s*next\\s+turn\\s*"),
    Plant("\\s*plant\\s+-s\\s+(?<seed>.*\\S)\\s+-d\\s+(?<direction>.*\\S)\\s*"),
    ShowPlant("\\s*showplant\\s+-l\\s+(?<x>-?\\d+)\\s+(?<y>-?\\d+)\\s*"),
    HowMuchWater("\\s*howmuch\\s+water\\s*"),
    // Time & Date Commands
    TIME("\\s*time\\s*"),
    DATE("\\s*date\\s*"),
    DATETIME("\\s*datetime\\s*"),
    DAY_OF_WEEK("\\s*day\\s+of\\s+the\\s+week\\s*"),

    // Cheat time/date advance
    CHEAT_ADVANCE_TIME("\\s*cheat\\s+advance\\s+time\\s+(-?\\d+)h\\s*"),
    CHEAT_ADVANCE_DATE("\\s*cheat\\s+advance\\s+date\\s+(-?\\d+)d\\s*"),

    // Show Map
    PrintMap("\\s*print\\s+map\\s+-l\\s+(.*)\\s+(.*)\\s+(.*)\\s*"),
    HelpReadingMap("\\s*help\\s+reading\\s+map\\s*"),

    //Energy
    EnergyShow("\\s*energy\\s+show\\s*"),
    EnergySet("\\s*energy\\s+set\\s+-v\\s+(.*)\\s*"),
    EnergyUnlimited("\\s*energy\\s+unlimited\\s*"),

    // Season
    SEASON("\\s*season\\s*"),

    // Weather
    WEATHER("\\s*weather\\s*"),
    WEATHER_FORECAST("\\s*weather\\s+forecast\\s*"),
    CHEAT_WEATHER_SET("\\s*cheat\\s+weather\\s+set\\s+(Sunny|Rain|Storm|Snow)\\s*"),

    // Thunder cheat
    CHEAT_THOR("\\s*cheat\\s+Thor\\s+-l\\s+(\\d+)\\s*,\\s*(\\d+)\\s*"),

    // Greenhouse
    GREENHOUSE_BUILD("\\s*greenhouse\\s+build\\s*"),

    COOKINGREFRIGERATOR("\\s*cooking refrigerator \\s*(put|pick) \\s*([^\\s]+)\\s*"),
    COOKINGSHOWRECIPES("\\s*cooking\\s+show\\s+recipes\\s*"),

    COOKINGPREPARE("\\s*cooking\\s+prepare\\s+([\\w\\s]+)\\s*"),
    EAT("eat\\s+([^\\s].*[^\\s]|[^\\s]+)");
    private final String pattern;
    GameMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMather(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);

        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
