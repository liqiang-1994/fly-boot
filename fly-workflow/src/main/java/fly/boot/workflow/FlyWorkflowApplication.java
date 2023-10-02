package fly.boot.workflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FlyWorkflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlyWorkflowApplication.class, args);
    }

}
