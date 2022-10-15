package dtfb.bot.commands.slash.impl;

import dtfb.bot.commands.slash.SlashCommand;
import dtfb.forum.DiscordChannel;
import dtfb.forum.DiscordChannelThread;
import dtfb.forum.DiscordUser;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.util.ArrayList;
import java.util.List;

public class ScrubSlashCommand extends SlashCommand {
    @Override
    public void handle(SlashCommandEvent event) {

        //TODO: Start from category

        List<TextChannel> channels = event.getGuild().getTextChannels();
        List<DiscordChannel> discordChannels = new ArrayList();
        channels.forEach(channel -> {
            List<DiscordChannelThread> threads = new ArrayList<>();
            channel.getThreadChannels().forEach(threadChannel -> {
                User user = threadChannel.getOwner().getUser();
                DiscordUser discordUser = new DiscordUser(user.getId(), user.getName(), user.getDiscriminator());
                DiscordChannelThread discordChannelThread = new DiscordChannelThread(threadChannel.getId(), threadChannel.getName(), discordUser);
                threads.add(discordChannelThread);
            });
            discordChannels.add(new DiscordChannel(channel.getId(), channel.getName(), threads));
        });

        // testing
        for(DiscordChannel channel : discordChannels) {
            System.out.println(channel.name());
            channel.threads().forEach(t -> {
                System.out.println(" ".repeat(4) + t.name());
            });
        }
    }

    @Override
    public String getDescription() {
        return "Scrubs all the channels and messages on the server and adds them into a database.";
    }
}