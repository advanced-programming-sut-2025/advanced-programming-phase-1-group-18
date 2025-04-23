package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {
    MenuEnter("\\s*menu\\s+enter\\s+(?<menuName>.*\\S)\\s*"),
    ShowCurrentMenu("\\s*show\\s+current\\s+menu\\s*"),
    Logout("\\s*user\\s+logout\\s*");

    private final String pattern;

    MainMenuCommands(String pattern) {
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