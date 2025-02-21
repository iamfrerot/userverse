package dev.frerot.userverse.configuration;

import io.swagger.v3.oas.models.servers.Server;

import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${springdoc.server.url}")
    private String serverUrl;
    @Value("${springdoc.server.description}")
    private String serverDescription;



    @Bean
    public OpenAPI openAPI() {

        // Info or api
        Info info = new Info().title("Userverse").description(
                "Welcome to the Userverse API! The Userverse API is currently under development and provides endpoints for data analysis. This API is open for everyone to use, and you can contribute to its development!.")
                .contact(new Contact().name("frérot").url("https://frerot.dev"))
                .license(new License().name("MIT").url("https://github.com/iamfrerot/userverse/blob/main/LICENSE"));

        Server server= new Server().url(serverUrl).description(serverDescription);
        
        return new OpenAPI()
                .info(info)
                .servers(List.of(server));
    }
}