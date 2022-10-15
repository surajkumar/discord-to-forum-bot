package dtfb.persistance.repository;

import dtfb.persistance.entity.DiscordCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscordCategoryRepository extends JpaRepository<DiscordCategory, Long> {

    List<DiscordCategory> findByServerId(String serverId);

}