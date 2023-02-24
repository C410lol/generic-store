package com.api.genericstore.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationConfigurations {

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH-mm-ss'Z'";
    public static final LocalDateTimeSerializer LOCAL_DATETIME_SERIALIZER =
            new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_FORMAT));

    @Bean
    public ObjectMapper objectMapper() {
        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addSerializer(LOCAL_DATETIME_SERIALIZER);
        return new ObjectMapper().registerModule(timeModule);
    }

    @Bean
    public LocalDateTime localDateTime() {
        return LocalDateTime.now(ZoneId.of("UTC"));
    }
}
