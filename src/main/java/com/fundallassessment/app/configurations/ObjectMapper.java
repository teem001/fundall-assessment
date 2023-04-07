package com.fundallassessment.app.configurations;

import io.github.cdimascio.dotenv.Dotenv;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapper {
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

    @Bean
    Dotenv dotenv(){
        return Dotenv.load();
    }

}