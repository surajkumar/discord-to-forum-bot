package dtfb.controller;

import dtfb.entity.CmChannel;
import dtfb.entity.ServerStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Controller
@EnableAsync
public class ChannelController {
    private static final String API_URL = "http://bot/api/v1/forum/channels";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/forum/channel/{channelId}")
    @Async
    public CompletableFuture<String> getChannel(Model model, @PathVariable("channelId") long channelId) {
        CmChannel channel = restTemplate.getForEntity(API_URL + "/" + channelId + "/messages", CmChannel.class).getBody();
        model.addAttribute("channel", channel);
        return CompletableFuture.completedFuture("channel");
    }

}