package dtfb.bot.commands.slash;

import dtfb.bot.commands.slash.impl.PingSlashCommand;
import dtfb.bot.commands.slash.impl.ScrubSlashCommand;
import dtfb.bot.commands.slash.impl.ServerURLSlashCommand;

import java.util.HashMap;
import java.util.Map;

public class SlashCommandRepository {
    private static final Map<String, SlashCommand> COMMANDS = new HashMap<>();
    static {
        COMMANDS.put("ping", new PingSlashCommand());
        COMMANDS.put("google", new ServerURLSlashCommand());
        COMMANDS.put("googleit", new ScrubSlashCommand());
    }

    public static SlashCommand get(String input) {
        return COMMANDS.get(input);
    }

    public static Map<String, SlashCommand> getCommands() {
        return COMMANDS;
    }
}