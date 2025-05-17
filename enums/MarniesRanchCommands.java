package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MarniesRanchCommands
{
    BUYANIMAL("\\s*buy\\s+animal\\s+-a\\s+(.+)\\s+-n\\s+(.+)\\s*");

    private final String pattern;
    MarniesRanchCommands(String pattern) {
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