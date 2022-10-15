package dtfb.persistance.repository;

import dtfb.persistance.entity.DiscordMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscordMessageRepository extends JpaRepository<DiscordMessage, Long> {
    List<DiscordMessage> findByChannelId(long channelId);
}