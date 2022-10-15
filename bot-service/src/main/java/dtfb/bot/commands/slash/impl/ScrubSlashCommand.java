package dtfb.bot.commands.slash.impl;

import dtfb.bot.commands.slash.SlashCommand;
import dtfb.persistance.entity.*;
import dtfb.persistance.repository.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class ScrubSlashCommand extends SlashCommand {
    private final Executor executor = Executors.newCachedThreadPool();

    @Autowired
    private DiscordCategoryRepository discordCategoryRepository;

    @Autowired
    private DiscordChannelRepository discordChannelRepository;

    @Autowired
    private DiscordChannelThreadRepository discordChannelThreadRepository;

    @Autowired
    private DiscordMessageRepository discordMessageRepository;

    @Autowired
    private DiscordUserRepository discordUserRepository;

    public ScrubSlashCommand() {}

    @Override
    public void handle(SlashCommandEvent event) {

        event.deferReply().queue();

        executor.execute(() -> {
            /* Loop over all the categories within a server */
            List<Category> categories = event.getGuild().getCategories();
            categories.forEach(category -> {

                /* Save the category information */
                DiscordCategory discordCategory = new DiscordCategory();
                discordCategory.setServerId(event.getGuild().getId());
                discordCategory.setCategoryId(category.getId());
                discordCategory.setName(category.getName());
                discordCategoryRepository.save(discordCategory);


                /* Loop over all text channels in category */
                List<TextChannel> channels = category.getTextChannels();
                List<DiscordChannel> discordChannels = new ArrayList<>();
                channels.forEach(channel -> {

                    /* Loop over threads in channel */
                    List<DiscordChannelThread> threads = new ArrayList<>();
                    channel.getThreadChannels().forEach(threadChannel -> {

                        /* User sending message in thread */
                        User user = threadChannel.getOwner().getUser();
                        DiscordUser discordUser = new DiscordUser();
                        discordUser.setUserId(discordUser.getUserId());
                        discordUser.setName(user.getName());
                        discordUser.setDiscriminator(user.getDiscriminator());
                        discordUserRepository.save(discordUser);

                        /* Thread in channel */
                        DiscordChannelThread discordChannelThread = new DiscordChannelThread();
                        discordChannelThread.setThreadId(threadChannel.getId());
                        discordChannelThread.setName(threadChannel.getName());
                        discordChannelThread.setChannelId(channel.getId());
                        threads.add(discordChannelThread);

                        /* Messages in thread */
                        List<DiscordMessage> threadMessages = new ArrayList<>();
                        threadChannel.getHistory().getRetrievedHistory().forEach(message -> {
                            threadMessages.add(toDiscordMessage(threadChannel.getId(), message));
                        });
                        discordMessageRepository.saveAll(threadMessages);

                    });
                    discordChannelThreadRepository.saveAll(threads);

                    /* The discord text channel */
                    DiscordChannel discordChannel = new DiscordChannel();
                    discordChannel.setCategoryId(category.getId());
                    discordChannel.setChannelId(channel.getId());
                    discordChannel.setName(channel.getName());
                    discordChannels.add(discordChannel);

                    /* The regular messages in a text channel */
                    List<DiscordMessage> channelMessages = new ArrayList<>();
                    channel.getHistory().getRetrievedHistory().forEach(message -> channelMessages.add(toDiscordMessage(channel.getId(), message)));
                    discordMessageRepository.saveAll(channelMessages);

                });
                discordChannelRepository.saveAll(discordChannels);
            });

            EmbedBuilder eb = new EmbedBuilder();
            eb.setAuthor("Ready", "https://google.com/", null);
            eb.setDescription("Your server has been setup and your newly created forum can be accessed by visiting http://localhost:8080/forum/" + event.getGuild().getId());
            eb.setColor(Color.RED);
            event.getHook().editOriginalEmbeds().setEmbeds(eb.build()).queue();
        });
    }

    /**
     * Converts a {@link net.dv8tion.jda.api.entities.Message} to a {@link dtfb.persistance.entity.DiscordMessage}.
     *
     * @param channelId The channel ID to associate with the new DiscordMessage.
     * @param message The message that will be converted.
     * @return A constructed DiscordMessage object representation of Message
     */
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