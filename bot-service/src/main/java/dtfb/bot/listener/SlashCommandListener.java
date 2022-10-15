package dtfb.bot.listener;

import dtfb.bot.commands.slash.SlashCommandRepository;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlashCommandListener extends DiscordEventListener {
    private static final Logger logger = LoggerFactory.getLogger(SlashCommandListener.class);

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        var author = event.getUser();
        if (!author.isBot()) {
            var input = event.getName();
            var command = SlashCommandRepository.get(input);
            if (command != null) {
                logger.info("Received slash command [" + input + "] from " + author.getName());
                command.handle(event);
            } else {
                logger.info("Unknown slash command [" + input + "] from " + author.getName());
                event.reply("Unknown command " + input).queue();
            }
        }
    }
}