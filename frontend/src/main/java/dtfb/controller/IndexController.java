package dtfb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/{serverId}")
    public String index(@PathVariable long serverId) {
        return "index";
    }

}