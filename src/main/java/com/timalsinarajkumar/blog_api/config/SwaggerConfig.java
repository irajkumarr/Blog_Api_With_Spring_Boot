package com.timalsinarajkumar.blog_api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    static {
        try {
            Class<?> currentUserIdClass = Class.forName("com.timalsinarajkumar.blog_api.security.CurrentUserId");
            SpringDocUtils.getConfig().addAnnotationsToIgnore(currentUserIdClass);
        } catch (ClassNotFoundException e) {
            // Log warning or handle gracefully if class path is wrong
        }
    }

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("My Application API")
                        .version("1.0")
                        .description("Protected REST API documentation"))
                // 1. Link the security requirement globally to all endpoints
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                // 2. Define the exact Bearer Token schema type
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT"))); // Shows "JWT" as a hint in the UI
    }
}

