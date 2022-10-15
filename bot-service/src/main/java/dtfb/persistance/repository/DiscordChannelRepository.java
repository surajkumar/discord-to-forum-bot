package dtfb.persistance.repository;

import dtfb.persistance.entity.DiscordChannel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscordChannelRepository extends JpaRepository<DiscordChannel, Long> {
    List<DiscordChannel> findByCategoryId(long categoryId);
}