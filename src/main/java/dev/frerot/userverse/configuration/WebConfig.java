package dev.frerot.userverse.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            public void addCorsMappings( CorsRegistry registry) {
                registry.addMapping("/**") // Allow CORS for all paths
                        .allowedOrigins("*") // Allow specific origins
                        .allowedMethods("GET", "POST") // Allow specific methods
                        .allowedHeaders("*") // Allow all headers
                        .allowCredentials(true); // Allow credentials
            }
        };
    }

}
