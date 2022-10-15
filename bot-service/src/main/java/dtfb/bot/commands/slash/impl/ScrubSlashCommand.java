package dtfb.bot.commands.slash.impl;

import dtfb.bot.commands.slash.SlashCommand;
import dtfb.persistance.entity.DiscordChannel;
import dtfb.persistance.entity.DiscordChannelThread;
import dtfb.persistance.entity.DiscordMessage;
import dtfb.persistance.entity.DiscordUser;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.util.ArrayList;
import java.util.List;

public class ScrubSlashCommand extends SlashCommand {
    @Override
    public void handle(SlashCommandEvent event) {

        //TODO: Save to DB

        List<Category> categories = event.getGuild().getCategories();
        categories.forEach(category -> {
            List<TextChannel> channels = category.getTextChannels();
            List<DiscordChannel> discordChannels = new ArrayList<>(); //persist
            channels.forEach(channel -> {
                List<DiscordChannelThread> threads = new ArrayList<>(); //persist
                channel.getThreadChannels().forEach(threadChannel -> {
                    User user = threadChannel.getOwner().getUser();
                    DiscordUser discordUser = new DiscordUser(); // persist
                    discordUser.setUserId(discordUser.getUserId());
                    discordUser.setName(user.getName());
                    discordUser.setDiscriminator(user.getDiscriminator());
                    DiscordChannelThread discordChannelThread = new DiscordChannelThread();
                    discordChannelThread.setThreadId(threadChannel.getId());
                    discordChannelThread.setName(threadChannel.getName());
                    discordChannelThread.setChannelId(channel.getId());
                    threads.add(discordChannelThread);

                    List<DiscordMessage> threadMessages = new ArrayList<>(); //persist
                    threadChannel.getHistory().getRetrievedHistory().forEach(message -> {
                        threadMessages.add(toDiscordMessage(threadChannel.getId(), message));
                    });

                });

                DiscordChannel discordChannel = new DiscordChannel(); //persist
                discordChannel.setCategoryId(category.getId());
                discordChannel.setChannelId(channel.getId());
                discordChannel.setName(channel.getName());
                discordChannels.add(discordChannel);

                List<DiscordMessage> channelMessages = new ArrayList<>(); //persist
                channel.getHistory().getRetrievedHistory().forEach(message -> {
                    channelMessages.add(toDiscordMessage(channel.getId(), message));
                });

            });
        });
    }

    private DiscordMessage toDiscordMessage(String channelId, Message message) {
        DiscordMessage discordMessage = new DiscordMessage();
        discordMessage.setChannelId(channelId);
        discordMessage.setUserId(message.getAuthor().getId());
        discordMessage.setMessage(message.getContentRaw());
        discordMessage.setTimestamp(message.getTimeCreated().toString());
        return discordMessage;
    }

    @Override
    public String getDescription() {
        return "Scrubs all the channels and messages on the server and adds them into a database.";
    }
}