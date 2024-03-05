package com.peerclient.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Auth {

    @Bean
    public Token generateToken(){
        return new Token();
    }

}
