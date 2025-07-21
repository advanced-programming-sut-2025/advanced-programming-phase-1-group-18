package io.github.group18.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MarketMenuEnums {
    MenuEnter("\\s*menu\\s+enter\\s+(?<menuName>.*\\S)\\s*"),
    SHOWALLPRODUCTS("\\s*show\\s+all\\s+products\\s*"),
    SHOWALLAVAILABLEPRODUCTS("\\s*show\\s+all\\s+available\\s+products\\s*"),
    PURCHASE("\\s*purchase\\s+(?<productName>.+?)(?:\\s+-n\\s+(?<count>\\d+))?\\s*"),
    CHEATADD("\\s*cheat\\s+add\\s+(?<count>\\d+)\\s+dollars\\s*");

    private final String pattern;


    MarketMenuEnums(String pattern) {
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
