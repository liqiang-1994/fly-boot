package fly.boot.system.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@AllArgsConstructor
public class OpenApiConfiguration {
    private final Environment environment;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(info());
    }
    private Info info() {
        return new Info()
                .title(environment.getProperty("spring.application.name"))
                .description(environment.getProperty("spring.application.name")+"接口文档")
                .version(environment.getProperty("spring.application.version"))
                .summary("fly-boot接口文档");
    }
}
