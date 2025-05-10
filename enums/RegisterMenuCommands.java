package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegisterMenuCommands {
    MenuEnter("\\s*menu\\s+enter\\s+(?<menuName>.*\\S)\\s*"),
    MenuExit("\\s*menu\\s+exit\\s*"),
    ShowCurrentMenu("\\s*show\\s+current\\s+menu\\s*"),
    //Register("\\s*register\\s+-u\\s+(?<username>\\w{3,})\\s+-p\\s+(?<password>(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=\\-{}\\[\\]:;\"'<>,.?/\\\\|]).{8,})\\s+(?<passwordConfirm>\\S+)\\s+-n\\s+(?<nickname>\\S+)\\s+-e\\s+(?<email>[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,})\\s+-g\\s+(?<gender>male|female)\\s*"),
    //Register("\\s*register\\s+-u\\s+(?<username>\\S+)\\s+-p\\s+(?<password>\\S+)\\s+(?<passwordConfirm>\\S+)\\s+-n\\s+(?<nickname>\\S+)\\s+-e\\s+(?<email>\\S+)\\s+-g\\s+(?<gender>\\S+)\\s*\n"),
    Register2("\\s*register\\s+-u\\s+(\\S+)\\s+-n\\s+(\\S+)\\s+-e\\s+(\\S+)\\s+-g\\s+(\\S+)\\s*"),
    Register("\\s*register\\s+-u\\s+(\\S+)\\s+-p\\s+(\\S+)\\s+(\\S+)\\s+-n\\s+(\\S+)\\s+-e\\s+(\\S+)\\s+-g\\s+(\\S+)\\s*");
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
