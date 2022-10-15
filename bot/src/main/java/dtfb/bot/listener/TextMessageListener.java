package dtfb.bot.listener;

import dtfb.bot.commands.text.TextCommandRepository;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextMessageListener extends DiscordEventListener {
    private static final Logger logger = LoggerFactory.getLogger(TextMessageListener.class);

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(!event.getAuthor().isBot()) {
            String content = event
                    .getMessage()
                    .getContentRaw()
                    .trim();
            if(content.startsWith("!")) {
                var cmd = content.split(" ")[0].replace("!", "").trim();
                var input = content.replace(String.format("!%s", cmd), "").trim();
                var command = TextCommandRepository.get(cmd);
                if (command != null) {
                    logger.info("Received command [" + cmd + "] from "
                            + event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator());
                    command.handle(event, input);
                }
            }

            String serverId = event.getGuild().getId();
            String serverName = event.getGuild().getName();
            String message = content;
            String author = event.getMessage().getAuthor().getName() + "#" + event.getMessage().getAuthor().getDiscriminator();
            String timestamp = event.getMessage().getTimeCreated().toString();
            String channelId = event.getChannel().getId();
            String channelName = event.getChannel().getName();


            try {

                String threadId = event.getThreadChannel().getId();
                String threadName = event.getThreadChannel().getName();
                String parentChannelId = event.getThreadChannel().getParentChannel().getId();
                String parentChannel = event.getThreadChannel().getParentChannel().getName();

            } catch (Exception e) {
                // not a thread
            }


        }
    }
}