package dtfb.bot.commands.text.impl;

import dtfb.bot.commands.text.TextCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PingTextCommand implements TextCommand {

    @Override
    public void handle(MessageReceivedEvent event, String input) {
        event.getTextChannel().sendMessage("Pong!").queue();
    }
}