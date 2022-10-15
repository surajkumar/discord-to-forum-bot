package dtfb.bot.commands.text;

import dtfb.bot.commands.text.impl.PingTextCommand;

import java.util.HashMap;
import java.util.Map;

public class TextCommandRepository {
    private static final Map<String, TextCommand> COMMANDS = new HashMap<>();
    static {
        COMMANDS.put("ping", new PingTextCommand());
    }

    public static TextCommand get(String key) {
        return COMMANDS.get(key);
    }
}