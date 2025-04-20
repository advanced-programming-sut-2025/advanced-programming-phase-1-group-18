package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegisterMenuCommands {
    MenuEnter("\\s*menu\\s+enter\\s+(?<menuName>.*\\S)\\s*"),
    MenuExit("\\s*menu\\s+exit\\s*"),
    ShowCurrentMenu("show\\s+current\\s+menu"),
    Register("\\s*register\\s+-u\\s+(?<username>\\w{3,})\\s+-p\\s+(?<password>(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=\\-{}\\[\\]:;\"'<>,.?/\\\\|]).{8,})\\s+(?<passwordConfirm>\\S+)\\s+-n\\s+(?<nickname>\\S+)\\s+-e\\s+(?<email>[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,})\\s+-g\\s+(?<gender>male|female)\\s*"),
    PickQuestion("Salam-Aleikom-Soosan-Khanoom");
    private final String pattern;

    RegisterMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);

        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
