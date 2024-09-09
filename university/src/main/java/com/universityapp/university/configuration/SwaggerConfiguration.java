package com.universityapp.university.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("University API")
                        .version("1.0")
                        .description("API for managing university courses and authors")
                        .contact(new Contact()
                                .name("Kareem")
                                .email("karimyasser337@gmail.com")
                                .url("https://sumerge.com")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("university-public")
                .pathsToMatch("/courses/**", "/authors/**")
                .build();
    }
}
