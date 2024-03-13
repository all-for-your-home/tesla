package com.example.allforyourhome.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH");
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(
                new SecurityRequirement().addList("Bearer Authentication")
        ).components(
                new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme())
        ).info(
                new Info()
                        .title("DB-search API")
                        .description("This api was generated using springdoc for DB-search app")
                        .version("1.0")
                        .contact(
                                new Contact()
                                        .name("Dilshod Latipov")
                                        .email("dilshodlatipov748@gmail.com")
                        ).license(
                                new License()
                                        .name("Licence of API")
                                        .url("API licence url")
                        )
        );
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

    @Bean
    public SnsClient snsClient() {
        return SnsClient.builder().region(Region.EU_WEST_1).build();
    }
}
