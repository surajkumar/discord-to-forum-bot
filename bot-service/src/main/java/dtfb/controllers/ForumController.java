package dtfb.controllers;

import dtfb.controllers.structure.CategoryStructure;
import dtfb.controllers.structure.ChannelStructure;
import dtfb.controllers.structure.ServerStructure;
import dtfb.controllers.structure.ThreadStructure;
import dtfb.persistance.entity.*;
import dtfb.persistance.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/forum")
public class ForumController {

    @Autowired
    private DiscordCategoryRepository categoryRepository;

    @Autowired
    private DiscordChannelRepository channelRepository;

    @Autowired
    private DiscordChannelThreadRepository channelThreadRepository;

    @Autowired
    private DiscordMessageRepository messageRepository;

    @Autowired
    private DiscordUserRepository userRepository;

    @Autowired
    private DiscordServerRepository serverRepository;

    @GetMapping("/{serverId}")
    public ResponseEntity<ServerStructure> getServer(@PathVariable long serverId) {
        ServerStructure serverStructure = new ServerStructure();

        List<CategoryStructure> categoryStructure = new ArrayList<>();
        categoryRepository.findByServerId(serverId).forEach(c -> {
            CategoryStructure cats = new CategoryStructure();
            cats.setCategoryId(c.getCategoryId());
            cats.setCategoryName(c.getName());

            List<ChannelStructure> channelStructure = new ArrayList<>();
            channelRepository.findByCategoryId(c.getCategoryId()).forEach(channel -> {
                ChannelStructure cs = new ChannelStructure();
                cs.setChannelId(channel.getChannelId());
                cs.setChannelName(channel.getName());


                List<ThreadStructure> threadStructures = new ArrayList<>();
                channelThreadRepository.findByChannelId(channel.getChannelId()).forEach(t -> {
                    ThreadStructure ts = new ThreadStructure();
                    ts.setThreadId(t.getThreadId());
                    ts.setThreadName(t.getName());
                    threadStructures.add(ts);
                });
                cs.setThreads(threadStructures);
                channelStructure.add(cs);
            });

            cats.setChannels(channelStructure);
            categoryStructure.add(cats);
        });

        serverStructure.setServerId(serverId);
        serverStructure.setServerName(serverRepository.findByServerId(serverId).getServerName());
        serverStructure.setCategories(categoryStructure);

        return new ResponseEntity<>(serverStructure, HttpStatus.OK);
    }

    @GetMapping("/{serverId}/categories")
    public ResponseEntity<List<DiscordCategory>> getCategories(@PathVariable long serverId) {
        return new ResponseEntity<>(categoryRepository.findByServerId(serverId), HttpStatus.OK);
    }

    @GetMapping("/channels/{categoryId}")
    public ResponseEntity<List<DiscordChannel>> getChannels(@PathVariable long categoryId) {
        return new ResponseEntity<>(channelRepository.findByCategoryId(categoryId), HttpStatus.OK);
    }

    @GetMapping("/channels/{channelId}/messages")
    public ResponseEntity<List<DiscordMessage>> getChannelMessages(@PathVariable long channelId) {
        return new ResponseEntity<>(messageRepository.findByChannelId(channelId), HttpStatus.OK);
    }

    @GetMapping("/channels/{channelId}/threads")
    public ResponseEntity<List<DiscordChannelThread>> getChannelThreads(@PathVariable long channelId) {
        return new ResponseEntity<>(channelThreadRepository.findByChannelId(channelId), HttpStatus.OK);
    }
    @GetMapping("/channels/{channelId}/threads/{threadId}/messages")
    public ResponseEntity<List<DiscordMessage>> getChannelThreadMessages(@PathVariable long threadId) {
        return new ResponseEntity<>(messageRepository.findByChannelId(threadId), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<DiscordUser> getUser(@PathVariable long userId) {
        return new ResponseEntity<>(userRepository.findByUserId(userId), HttpStatus.OK);
    }
}