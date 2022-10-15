package dtfb.persistance.repository;

import dtfb.persistance.entity.DiscordChannel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscordChannelRepository extends JpaRepository<DiscordChannel, Long> {
}