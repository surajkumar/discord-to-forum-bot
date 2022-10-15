package dtfb.bot.commands.text;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface TextCommand {
    void handle(MessageReceivedEvent event, String input);
}