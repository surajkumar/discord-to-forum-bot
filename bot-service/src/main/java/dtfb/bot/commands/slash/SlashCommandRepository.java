package dtfb.bot.commands.slash;

import dtfb.bot.commands.slash.impl.PingSlashCommand;
import dtfb.bot.commands.slash.impl.ServerURLSlashCommand;

import java.util.HashMap;
import java.util.Map;

public class SlashCommandRepository {
    private static final Map<String, SlashCommand> COMMANDS = new HashMap<>();
    static {
        COMMANDS.put("ping", new PingSlashCommand());
        COMMANDS.put("google", new ServerURLSlashCommand());
    }

    public static void put(String name, SlashCommand command) {
        COMMANDS.put(name, command);
    }

    public static SlashCommand get(String input) {
        return COMMANDS.get(input);
    }

    public static Map<String, SlashCommand> getCommands() {
        return COMMANDS;
    }
}