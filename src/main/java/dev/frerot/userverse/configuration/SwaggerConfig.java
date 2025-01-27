package dev.frerot.userverse.configuration;



import io.swagger.v3.oas.models.servers.Server;

import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        // Info or api
        Info info = new Info().title("Userverse").description(
                "Welcome to the Userverse API! The Userverse API is currently under development and provides endpoints for data analysis. This API is open for everyone to use, and you can contribute to its development!.")
                .contact(new Contact().name("frérot").url("https://frerot.dev"))
                .license(new License().name("MIT").url("https://github.com/iamfrerot/userverse/blob/main/LICENSE"));

        Server localServer = new Server().url("http://localhost:2000").description("Local Server");
        Server nestServer= new Server().url("https://userverse.frerot.hackclub.app").description("Nest Server");
        return new OpenAPI()
                .info(info)
                .servers(List.of(nestServer,localServer));
    }
}