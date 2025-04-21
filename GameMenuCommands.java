package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    ShowCurrentMenu("\\s*show\\s+current\\s+menu\\s*"),
    GameNew("\\s*game\\s+new\\s+-u\\s+\\w+(\\s+\\w+)?(\\s+\\w+)?"),
    ExitGame("\\s*exit\\s+game\\s*"),
    VoteTerminateGame("\\s*vote\\s+terminate\\s+game\\s*"),
    NextTurn("\\s*next\\s+turn\\s*");
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
