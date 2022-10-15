package dtfb.controller;

import dtfb.entity.ServerStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Controller
@EnableAsync
public class IndexController {

    private static final String API_URL = "http://bot/api/v1/forum/{serverId}";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping()
    public String index() {
        return "index";
    }


    @RequestMapping("/forum")
    @Async
    public CompletableFuture<String> forum(Model model, @RequestParam("serverId") long serverId) {
        ServerStructure server = restTemplate.getForEntity(API_URL, ServerStructure.class, serverId).getBody();
        model.addAttribute("server", server);
        return CompletableFuture.completedFuture("forum");
    }

}