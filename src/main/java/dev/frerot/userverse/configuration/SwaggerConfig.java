package dev.frerot.userverse.configuration;



import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        // Info or api
        Info info = new Info().title("Userverse Api").version("1.0").description(
                "Welcome to the Userverse API! The Userverse API is a free API that provides endpoints for data analysis. You can fetch all users, retrieve users based on their country, and get a user by their ID. This API is open for everyone to use, and you can contribute to its development!.")
                .contact(new Contact().name("frerot").url("https://frerot.dev"))
                .license(new License().name("MIT").url("https://opensource.org/licenses/MIT"));

        return new OpenAPI()
                .info(info);
    }
}