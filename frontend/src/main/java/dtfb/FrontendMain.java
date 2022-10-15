package dtfb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

public class FrontendMain {
    public static void main(String[] args) {
        SpringApplication.run(FrontendMain.class, args);
    }

}