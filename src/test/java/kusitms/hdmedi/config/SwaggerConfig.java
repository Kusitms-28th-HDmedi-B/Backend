package kusitms.hdmedi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi groupedOpenApi() {
        GroupedOpenApi v1 = GroupedOpenApi.builder()
                .group("v1")
                .pathsToMatch("/api/**")
                .build();
        return v1;
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("HDmedi API")
                        .description("큐시즘 HDmedi API 명세서입니다.")
                        .version("v0.0.1"));
    }
}
