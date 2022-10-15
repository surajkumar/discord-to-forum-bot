package dtfb;

import dtfb.bot.BotStrategy;
import dtfb.bot.JdaBotStrategy;
import dtfb.bot.commands.slash.SlashCommandRepository;
import dtfb.bot.commands.slash.impl.ScrubSlashCommand;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Base64;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories
public class BotMain {
    /** This is for testing purposes */
    private static final String BASE_64_ENCODED_BOT_TOKEN = "T1RZeU5qWTROekkwTVRRNE5qTXpOalF4Lkc0TEVGZi44QVBIOUJiMlZtRHZ5LTFwZDNDSHJrQnpnN3JPamNZeHdtNEdmZw==";

    @Autowired
    public ScrubSlashCommand scrubSlashCommand;

    public static void main(String[] args) {
        SpringApplication.run(BotMain.class, args);
    }

    @Bean
    InitializingBean startBot() {
        return () -> {
            SlashCommandRepository.put("googleit", scrubSlashCommand);
            BotStrategy strategy = new JdaBotStrategy(new String(Base64.getDecoder().decode(BASE_64_ENCODED_BOT_TOKEN))); //test
            strategy.setup();
        };
    }
}