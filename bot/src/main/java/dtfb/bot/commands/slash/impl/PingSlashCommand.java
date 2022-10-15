package dtfb.bot.commands.slash.impl;

import dtfb.bot.commands.slash.SlashCommand;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class PingSlashCommand extends SlashCommand {

    @Override
    public void handle(SlashCommandEvent event) {
        event.reply("Pong!").queue();
    }

    @Override
    public String getDescription() {
        return "A command to check if the bot is still alive.";
    }
}