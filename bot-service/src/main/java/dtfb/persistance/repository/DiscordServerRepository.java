package dtfb.persistance.repository;

import dtfb.persistance.entity.DiscordServer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscordServerRepository extends JpaRepository<DiscordServer, Long> {
    DiscordServer findByServerId(long serverId);

}