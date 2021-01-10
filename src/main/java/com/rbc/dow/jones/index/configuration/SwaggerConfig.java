package com.rbc.dow.jones.index.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    //Open API Swagger bean to add information of project on the Swagger UI
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(info()).components(new Components());
    }

    private Info info(){
        Info info = new Info().title("Dow Jones Index Application")
                .description("This application provides functionality to upload the index data set, allows insertion of single record and also allows to retrieve record by stock ticker ");
        return info;
    }
}
