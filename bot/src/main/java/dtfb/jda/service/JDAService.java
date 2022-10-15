package dtfb.jda.service;


import dtfb.listener.MessageListener;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

/**
 * @author Giorgi
 */
@Slf4j
public class JDAService {

    /**
     * @param token String token for bot auth
     * @return ShardManager if successfully connected
     */
    public static ShardManager getShardManager(String token) {
        return DefaultShardManagerBuilder.createDefault(token)
                .setStatus(OnlineStatus.ONLINE)
                .addEventListeners(new MessageListener())
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();
    }
}
