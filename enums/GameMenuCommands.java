package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    WHOAMI("\\s*who\\s+am\\s+i\\s*"),
    ShowCurrentMenu("\\s*show\\s+current\\s+menu\\s*"),
    GameNew("^\\s*game\\s+new\\s+-u\\s+(\\w+)\\s*(\\w*)\\s*(\\w*)\\s*(\\w*)\\s*$"),
    ExitGame("\\s*exit\\s+game\\s*"),
    VoteTerminateGame("\\s*vote\\s+terminate\\s+game\\s*"),
    NextTurn("\\s*next\\s+turn\\s*"),
    Plant("\\s*plant\\s+-s\\s+(?<seed>.*\\S)\\s+-d\\s+(?<direction>.*\\S)\\s*"),
    ShowPlant("\\s*showplant\\s+-l\\s+(?<x>-?\\d+)\\s+(?<y>-?\\d+)\\s*"),
    HowMuchWater("\\s*howmuch\\s+water\\s*"),
    Fertilize("\\s*fertilize\\s+-f\\s+(.+)\\s+-d\\s+(.+)\\s*"),

    // Time & Date Commands
    TIME("\\s*time\\s*"),
    DATE("\\s*date\\s*"),
    DATETIME("\\s*datetime\\s*"),
    DAY_OF_WEEK("\\s*day\\s+of\\s+the\\s+week\\s*"),

    // Cheat time/date advance
    CHEAT_ADVANCE_TIME("\\s*cheat\\s+advance\\s+time\\s+(-?\\d+)h\\s*"),
    CHEAT_ADVANCE_DATE("\\s*cheat\\s+advance\\s+date\\s+(-?\\d+)d\\s*"),

    // Show Map
    PrintMap("\\s*print\\s+map\\s+-l\\s+(\\d+)\\s+(\\d+)\\s+-s\\s+(\\d+)\\s+(\\d+)\\s*"),
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

    Walk("\\s*walk\\s+-l\\s+\\s*(-?\\d+)\\s*,\\s*(-?\\d+)\\s*"),

    INVENTORYSHOW("\\s*inventory\\s+show\\s*"),
    INVENTORYTRASH("\\s*inventory\\s*trash\\s+-i\\s+(.*)\\s+-n\\s+(\\d+)\\s*"),

    COOKINGREFRIGERATOR("\\s*cooking\\s+refrigerator\\s+(put|pick)\\s+(.+)\\s*"),
    COOKINGSHOWRECIPES("\\s*cooking\\s+show\\s+recipes\\s*"),
    COOKINGPREPARE("\\s*cooking\\s+prepare\\s+([\\w\\s]+)\\s*"),
    EAT("eat\\s+(.+)\\s*"),
    SELL("\\s*sell\\s+(?<productName>.+?)(?:\\s+-n\\s+(?<count>\\d+))?\\s*"),

    MEETNPC("^\\s*meet\\s+NPC\\s+(.+?)\\s*$"),
    GIFTNPC("^\\s*gift\\s+NPC\\s+(.+)\\s+-i\\s+(.+)\\s*$"),
    FRIENDSHIPNPCLIST("^\\s*friendship\\s+NPC\\s+list\\s*$"),
    QUESTSLIST("^\\s*quests\\s+list\\s*$"),
    QUESTSFINISH("^\\s*quests\\s+finish\\s+-i\\s+(\\d+)\\s*$"),

    FriendShip("\\s*friendships\\s*"),
    Talk("\\s*talk -u (?<username>.*) -m (?<message>.*)\\s*"),
    TalkHistory("\\s*talk\\s+history -u (?<username>.*)\\s*"),
    Gift("\\s*gift -u (?<username>.*) -i (?<item>.*) -a (?<amount>.*)\\s*"),
    GiftList("\\s*gift\\s+list\\s*"),
    GiftRate("\\s*gift\\s*rate\\s*-i\\s*(?<giftNumber>.*)\\s*-r\\s*(?<rate>.*)\\s*"),
    GiftHistory("\\s*gift\\s+history\\s+-u (?<username>.*)\\s*"),
    Hug("\\s*hug\\s+-u\\s+(?<username>.*)\\s*"),
    Flower("\\s*flower -u (?<username>.*)\\s*"),

    SHOW_RECIPES("\\s*crafting\\s+show\\s+recipes\\s*"),
    CRAFT_ITEM("\\s*crafting\\s+craft\\s+(.*+)\\s*"),
    PLACE_ITEM("\\s*place\\s+item\\s+-n\\s+(.*+)\\s+-d\\s+(.*)\\s*"),
    CHEAT_ADD_ITEM("\\s*cheat\\s+add\\s+item\\s+-n\\s+(.*)\\s+-c\\s+([0-9]+)\\s*"),

    CRAFT_INFO("\\s*craftinfo\\s+-n\\s*(.*)\\s*"),
    Fishing("\\s*fishing\\s+-p\\s+(.*\\S)\\s*"),
    ArtisanUse("^artisan use (\\w+)(?:\\s+(\\w+))+$"),
    ArtisanGet("\\s*artisan\\s+get\\s+(.*)\\s*"),

    MenuEnter("\\s*menu\\s+enter\\s+(?<menuName>.*\\S)\\s*"),


    //tools commands
    TOOLEQUIP("\\s*tools\\s+equip\\s+(\\w+)"),
    TOOLSSHOWCURRENT("\\s*tools\\s+show\\s+current\\s*"),
    TOOLSSHOWAVAILABLE("\\s*tools\\s+show\\s+available\\s*"),
    TOOLSUPGRADE("\\s*tools\\s+upgrade\\s*(\\w+)\\s*"),
    TOOLSUSE("\\s*tools\\s+use\\s+-d\\s*(\\w+)\\s*"),

    // trade commands
    ASKMARRIAGE("ask marriage -u (\\w+) -r (\\w+)\\s*"),
    RESPOND("\\s*respond\\s+(-accept|-reject)\\s*-u\\s*(\\w+)"),
    SHOWMYMARRIAGEPROPOSALS("\\s*show\\s+my\\s+marriage\\s+proposals\\s*"),
    STARTTRADE("\\s*start\\s*trade\\s*"),
    TRADING("^\\s*trade\\s+-u\\s+(.+)\\s+-t\\s+(.+)\\s+-i\\s+(.+)\\s+-a\\s+(\\d+)(?:\\s+-p\\s+(\\d+)|\\s+-ti\\s+(.+)\\s+-ta\\s+(\\d+))$"),
    TRADELIST("\\s*trade\\s+list"),
    // In baba taghiir karda
    TRADERESPONSE("\\s*trade\\s+response\\s*-(accept|reject)\\s*-i (\\d+)"),
    TRADEHISTORY("\\s*trade\\s*history\\s*"),

    //Animal Commands
    SHEPHERDOUT("\\s*shepherd\\s+animals\\s+out\\s+-n\\s+(.+)\\s+-l\\s+(\\d+)\\s*,\\s*(\\d+)\\s*"),
    SHEPHERDIN("\\s*shepherd\\s+animals\\s+in\\s+-n\\s+(.+)"),
    ANIMALSINFORMATIONS("\\s*animals\\s*"),
    CHEATSETFRIENSHIPWITHANIMAL("\\s*cheat\\s+set\\s+friendship\\s+-n\\s+(\\w+)\\s*-c\\s*(\\d+)"),
    FEEDHAY("\\s*feed\\s+hay\\s+-n\\s*(.+)\\s*"),
    PET("\\s*pet\\s+-n\\s*(.+)\\s*"),
    SELLANIMAL("\\s*sell\\s+animal\\s*-n\\s*(\\w+)\\s*");




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
