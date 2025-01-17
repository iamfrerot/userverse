package dev.frerot.userverse.configuration;


import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
//        Info
        Info info = new Info().title("Userverse Api").version("1.0").description(
                "Welcome to the Userverse API! The Userverse API is a free API that provides endpoints for managing users. You can fetch all users, retrieve users based on their country, create new users, and get a user by their ID. This API is open for everyone to use, and you can contribute to its development!.")
                .contact(new Contact().name("frerot").url("https://frerot.dev"))
                .license(new License().name("MIT").url("https://opensource.org/licenses/MIT"));

//        Servers list

        List<Server> servers = new ArrayList<>();
        servers.add(new Server().url("https://userverse.frerot.hackclub.app").description("hosted on hackclub nest server"));
        servers.add(new Server().url("http://localhost:2000").description("Locale testing server"));

        return new OpenAPI()
                .info(info)
                .servers(servers);
    }
}