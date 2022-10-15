package dtfb.persistance.repository;

import dtfb.persistance.entity.DiscordMessage;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DiscordMessageRepository extends PagingAndSortingRepository<DiscordMessage, Long> {
    List<DiscordMessage> findByChannelId(long channelId);
}