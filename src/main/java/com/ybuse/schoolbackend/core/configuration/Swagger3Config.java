package com.ybuse.schoolbackend.core.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class Swagger3Config {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("School Backend API")
                        .description("RESTful风格后端API")
                        .version("v1")
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
                ).externalDocs(new ExternalDocumentation().description("Swagger3(OpenAPI)常用注解参考").url("https://blog.csdn.net/qq_35425070/article/details/105347336"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("公开api")
                .packagesToScan("com.ybuse.schoolbackend.user.controller.open")
                .build();
    }

    @Bean
    public GroupedOpenApi normalUserApi() {
        return GroupedOpenApi.builder()
                .group("普通用户api")
                .packagesToScan("com.ybuse.schoolbackend.user.controller")
                .build();
    }
}
