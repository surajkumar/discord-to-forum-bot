package dtfb.controllers;

import dtfb.persistance.entity.*;
import dtfb.persistance.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/forum")
public class ForumController {

    @Autowired
    private DiscordCategoryRepository discordCategoryRepository;

    @Autowired
    private DiscordChannelRepository discordChannelRepository;

    @Autowired
    private DiscordChannelThreadRepository discordChannelThreadRepository;

    @Autowired
    private DiscordMessageRepository discordMessageRepository;

    @Autowired
    private DiscordUserRepository discordUserRepository;

    @GetMapping("/{serverId}/categories")
    public ResponseEntity<List<DiscordCategory>> getCategories(@PathVariable long serverId) {
        return new ResponseEntity<>(discordCategoryRepository.findByServerId(serverId), HttpStatus.OK);
    }

    @GetMapping("/channels/{categoryId}")
    public ResponseEntity<List<DiscordChannel>> getChannels(@PathVariable long categoryId) {
        return new ResponseEntity<>(discordChannelRepository.findByCategoryId(categoryId), HttpStatus.OK);
    }

    @GetMapping("/channels/{channelId}/messages")
    public ResponseEntity<List<DiscordMessage>> getChannelMessages(@PathVariable long channelId) {
        return new ResponseEntity<>(discordMessageRepository.findByChannelId(channelId), HttpStatus.OK);
    }

    @GetMapping("/channels/{channelId}/threads")
    public ResponseEntity<List<DiscordChannelThread>> getChannelThreads(@PathVariable long channelId) {
        return new ResponseEntity<>(discordChannelThreadRepository.findByChannelId(channelId), HttpStatus.OK);
    }
    @GetMapping("/channels/{channelId}/threads/{threadId}/messages")
    public ResponseEntity<List<DiscordMessage>> getChannelThreadMessages(@PathVariable long threadId) {
        return new ResponseEntity<>(discordMessageRepository.findByChannelId(threadId), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<DiscordUser> getUser(@PathVariable long userId) {
        return new ResponseEntity<>(discordUserRepository.findByUserId(userId), HttpStatus.OK);
    }
}