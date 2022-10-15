package dtfb.persistance.repository;

import dtfb.persistance.entity.DiscordMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscordMessageRepository extends JpaRepository<DiscordMessage, Long> {
}