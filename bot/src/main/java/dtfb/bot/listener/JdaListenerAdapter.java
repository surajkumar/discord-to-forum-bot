package dtfb.bot.listener;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JdaListenerAdapter extends ListenerAdapter {
    private final DiscordEventListener eventListener;

    public JdaListenerAdapter(DiscordEventListener eventListener) {
        this.eventListener = eventListener;
    }

}