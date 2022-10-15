package dtfb.bot.commands.slash.impl;

import dtfb.bot.commands.slash.SlashCommand;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class ServerURLSlashCommand extends SlashCommand {
    @Override
    public void handle(SlashCommandEvent event) {
        event.reply("https://localhost/server/" + event.getGuild().getId()).queue();
    }

    @Override
    public String getDescription() {
        return "The forums URL for this server!";
    }
}