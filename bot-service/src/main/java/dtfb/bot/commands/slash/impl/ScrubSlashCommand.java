package dtfb.bot.commands.slash.impl;

import dtfb.bot.commands.slash.SlashCommand;
import dtfb.persistance.entity.*;
import dtfb.persistance.repository.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class ScrubSlashCommand extends SlashCommand {
    private static final Logger logger = LoggerFactory.getLogger(ScrubSlashCommand.class);
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

    @Autowired
    private DiscordServerRepository discordServerRepository;

    public ScrubSlashCommand() {}

    @Override
    public void handle(SlashCommandEvent event) {
        logger.info("Received /scrub");

        event.deferReply().queue();

        executor.execute(() -> {

            DiscordServer server = new DiscordServer();
            server.setServerId(event.getGuild().getIdLong());
            server.setServerName(event.getGuild().getName());
            discordServerRepository.save(server);

            /* Loop over all the categories within a server */
            List<Category> categories = event.getGuild().getCategories();

            logger.debug("Server has {} categories", categories.size());

            categories.forEach(category -> {

                /* Only categories that have text channels to persist */
                if(category.getTextChannels().size() > 0) {

                    /* Save the category information */
                    DiscordCategory discordCategory = new DiscordCategory();
                    discordCategory.setCategoryId(category.getIdLong());
                    discordCategory.setServerId(server.getServerId());
                    discordCategory.setName(category.getName());
                    discordCategoryRepository.save(discordCategory);
                    logger.trace("Saved category with name {}", discordCategory.getName());


                    /* Loop over all text channels in category */
                    List<TextChannel> channels = category.getTextChannels();
                    List<DiscordChannel> discordChannels = new ArrayList<>();
                    logger.debug("Category has {} channels", channels.size());

                    channels.forEach(channel -> {

                        /* Loop over threads in channel */
                        List<DiscordChannelThread> threads = new ArrayList<>();
                        channel.getThreadChannels().forEach(threadChannel -> {

                            /* User sending message in thread */
                            User user = threadChannel.getOwner().getUser();
                            DiscordUser discordUser = new DiscordUser();
                            discordUser.setUserId(user.getIdLong());
                            discordUser.setName(user.getName());
                            discordUser.setDiscriminator(user.getDiscriminator());
                            discordUser.setProfilePictureUrl(user.getEffectiveAvatarUrl());
                            discordUserRepository.save(discordUser);

                            /* Thread in channel */
                            DiscordChannelThread discordChannelThread = new DiscordChannelThread();
                            discordChannelThread.setThreadId(threadChannel.getIdLong());
                            discordChannelThread.setName(threadChannel.getName());
                            discordChannelThread.setChannelId(channel.getIdLong());
                            threads.add(discordChannelThread);

                            /* Messages in thread */
                            List<DiscordMessage> threadMessages = new ArrayList<>();
                            MessageHistory.getHistoryFromBeginning(threadChannel).complete().getRetrievedHistory().forEach(message -> {
                                threadMessages.add(toDiscordMessage(threadChannel.getIdLong(), message));
                            });
                            logger.debug("Thread {} has {} messages", threadChannel.getName(), threadMessages.size());
                            discordMessageRepository.saveAll(threadMessages);
                        });
                        logger.debug("Channel has {} thread channels", threads.size());
                        discordChannelThreadRepository.saveAll(threads);

                        /* The discord text channel */
                        DiscordChannel discordChannel = new DiscordChannel();
                        discordChannel.setCategoryId(category.getIdLong());
                        discordChannel.setChannelId(channel.getIdLong());
                        discordChannel.setName(channel.getName());
                        discordChannels.add(discordChannel);
                        logger.trace("Discord channel has been saved");

                        /* The regular messages in a text channel */
                        List<DiscordMessage> channelMessages = new ArrayList<>();
                        MessageHistory.getHistoryFromBeginning(channel).complete().getRetrievedHistory().forEach(message -> channelMessages.add(toDiscordMessage(channel.getIdLong(), message)));
                        logger.debug("Channel has {} messages", channelMessages.size());
                        discordMessageRepository.saveAll(channelMessages);

                    });
                    discordChannelRepository.saveAll(discordChannels);
                }
            });

            EmbedBuilder eb = new EmbedBuilder();
            eb.setAuthor("Ready", "https://google.com/", null);
            eb.setDescription("Your server has been setup and your newly created forum can be accessed by visiting http://localhost:8080/forum?serverId=" + event.getGuild().getIdLong());
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
    private DiscordMessage toDiscordMessage(long channelId, Message message) {
        DiscordMessage discordMessage = new DiscordMessage();
        discordMessage.setMessageId(message.getIdLong());
        discordMessage.setChannelId(channelId);
        discordMessage.setUserId(message.getAuthor().getIdLong());
        discordMessage.setMessage(message.getContentRaw());
        discordMessage.setTimestamp(message.getTimeCreated().toString());
        return discordMessage;
    }

    @Override
    public String getDescription() {
        return "Scrubs all the channels and messages on the server and adds them into a database.";
    }
}