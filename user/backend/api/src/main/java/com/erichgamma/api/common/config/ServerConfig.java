package com.erichgamma.api.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.DateFormatter;


import java.text.DateFormat;

@Configuration
public class ServerConfig {
    @Bean
    public String datePattern(){
        return "yyyy-MM_dd'T'HH:mm:ss.XXX";
    }
    @Bean
    public DateFormatter defaultDateFormatter(){
        return new DateFormatter(datePattern());
    }
}
