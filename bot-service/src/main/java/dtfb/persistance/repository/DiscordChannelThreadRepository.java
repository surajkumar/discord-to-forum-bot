package dtfb.persistance.repository;

import dtfb.persistance.entity.DiscordChannelThread;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscordChannelThreadRepository extends JpaRepository<DiscordChannelThread, Long> {
}