package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands {
    MenuEnter("\\s*menu\\s+enter\\s+(?<menuName>.*\\S)\\s*"),
    MenuExit("\\s*menu\\s+exit\\s*"),
    ShowCurrentMenu("\\s*show\\s+current\\s+menu\\s*"),
    ChangeUsername("\\s*change\\s+username\\s+-u\\s+(?<username>.*\\S)\\s*"),
    ChangeNickname("\\s*change\\s+nickname\\s+-u\\s+(?<nickname>.*\\S)\\s*"),
    ChangeEmail("\\s*change\\s+email\\s+-e\\s+(?<email>.*\\S)\\s*"),
    ChangePassword("\\s*change\\s+password\\s+-p\\s+(?<newPassword>.*\\S)\\s+-o\\s+(?<oldPassword>.*\\S)\\s*"),
    ShowInfo("\\s*user\\s+info\\s*");

    private final String pattern;

    ProfileMenuCommands(String pattern) {
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