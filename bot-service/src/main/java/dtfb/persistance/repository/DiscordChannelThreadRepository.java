package dtfb.persistance.repository;

import dtfb.persistance.entity.DiscordChannelThread;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DiscordChannelThreadRepository extends PagingAndSortingRepository<DiscordChannelThread, Long> {
    List<DiscordChannelThread> findByChannelId(long channelId);
}