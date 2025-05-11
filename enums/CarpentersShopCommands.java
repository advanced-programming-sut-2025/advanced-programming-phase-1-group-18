package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CarpentersShopCommands
{
    MenuEnter("\\s*menu\\s+enter\\s+(?<menuName>.*\\S)\\s*"),
    SHOWALLPRODUCTS("\\s*show\\s+all\\s+products\\s*"),
    SHOWALLAVAILABLEPRODUCTS("\\s*show\\s+all\\s+available\\s+products\\s*"),
    BUILDBUILDING("\\s*build\\s+-a\\s+(.+)\\s+-l\\s+(\\d+)\\s+(\\d+)\\s*");

    private final String pattern;
    CarpentersShopCommands(String pattern) {
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
