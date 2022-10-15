package dtfb;

import dtfb.bot.BotStrategy;
import dtfb.bot.JdaBotStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories
public class BotMain {
    public static void main(String[] args) {
        SpringApplication.run(BotMain.class, args);
        BotStrategy strategy = new JdaBotStrategy("OTYyNjY4NzI0MTQ4NjMzNjQx.GH9rFu._zkXyBhWxHaD5qS70kJASeKILrKOZykt-QTibk");
        strategy.setup();
    }
}