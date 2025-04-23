package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum AvatarMenuCommands {
    MenuEnter("\\s*menu\\s+enter\\s+(?<menuName>.*\\S)\\s*"),;
    private final String pattern;

    AvatarMenuCommands(String pattern) {
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
