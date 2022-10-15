package dtfb.controllers;

import dtfb.persistance.entity.DiscordCategory;
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
    public ResponseEntity<List<DiscordCategory>> getCategories(@PathVariable String serverId) {
        return new ResponseEntity<>(discordCategoryRepository.findByServerId(serverId), HttpStatus.OK);
    }
}