package fly.boot.gateway;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Fly-boot API",
        description = "fly-boot接口文档",
        license = @License(name = "Apache 2.0"),
        contact = @Contact(name = "luxyva", url = "https://github.com/liqiang-1994", email = "luxyva@outlook.com"),
        version = "v1"),
        security = @SecurityRequirement(name = "Authorization"))
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "Authorization",
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SpringdocConfig {
}
