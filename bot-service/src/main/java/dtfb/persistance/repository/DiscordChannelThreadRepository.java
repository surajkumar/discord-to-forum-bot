package dtfb.persistance.repository;

import dtfb.persistance.entity.DiscordChannelThread;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscordChannelThreadRepository extends JpaRepository<DiscordChannelThread, Long> {
    List<DiscordChannelThread> findByChannelId(long channelId);
}