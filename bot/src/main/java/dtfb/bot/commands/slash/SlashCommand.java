package dtfb.bot.commands.slash;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class SlashCommand {
    public abstract void handle(SlashCommandEvent event);
    public abstract String getDescription();

    private final List<SlashCommandOption> options = new ArrayList<>();

    public void addOption(SlashCommandOption option) {
        options.add(option);
    }

    public SlashCommandOption[] getOptions() {
        return options.toArray(new SlashCommandOption[0]);
    }
}