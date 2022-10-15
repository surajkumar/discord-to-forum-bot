package dtfb.bot;

import dtfb.bot.listener.SlashCommandListener;
import dtfb.bot.listener.TextMessageListener;
import dtfb.bot.commands.slash.SlashCommandOption;
import dtfb.bot.commands.slash.SlashCommandRepository;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdaBotStrategy implements BotStrategy {
    private static final Logger logger = LoggerFactory.getLogger(JdaBotStrategy.class);
    private final String botToken;

    public JdaBotStrategy(String botToken) {
        this.botToken = botToken;
    }

    @Override
    public boolean setup() {
        boolean connected;
        try {
            JDA jda = JDABuilder.createDefault(botToken)
                    .addEventListeners(new TextMessageListener())
                   // .addEventListeners(new ReactionListener())
                    .addEventListeners(new SlashCommandListener())
                   // .addEventListeners(new UserJoinListener())
                    .setStatus(OnlineStatus.ONLINE)
                    .enableIntents(GatewayIntent.GUILD_MEMBERS)
                    .enableIntents(GatewayIntent.GUILD_MESSAGES)
                    .build()
                    .awaitReady();
            connected = jda.getStatus() == JDA.Status.CONNECTED;
            if(connected) {
                SlashCommandRepository.getCommands().forEach((k, v) -> {
                    jda.upsertCommand(new CommandData(k, v.getDescription())).queue();
                    /*for(SlashCommandOption option : v.getOptions()) {
                        jda.upsertCommand(new CommandData(k, v.getDescription())
                           .addOption(OptionType.valueOf(option.type().name()),
                                      option.name(),
                                     option.description()))
                           .queue();
                    }*/
                });
            }
        } catch (Exception e) {
            logger.error("Failed to setup bot using JDA", e);
            connected = false;
        }
        return connected;
    }
}