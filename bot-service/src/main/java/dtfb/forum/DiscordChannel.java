package dtfb.forum;

import java.util.List;

public record DiscordChannel(String id, String name, List<DiscordChannelThread> threads) {
}