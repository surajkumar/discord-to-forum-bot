package dtfb.persistance.repository;

import dtfb.persistance.entity.DiscordUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscordUserRepository extends JpaRepository<DiscordUser, Long> {
    DiscordUser findByUserId(long userId);
}