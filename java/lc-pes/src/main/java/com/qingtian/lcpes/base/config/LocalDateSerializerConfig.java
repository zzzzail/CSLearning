package com.qingtian.lcpes.base.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

/**
 * @author zhangxq
 * @since 2022/9/6
 */
@Configuration
@Slf4j
public class LocalDateSerializerConfig {
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(PATTERN)));
            builder.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(PATTERN)));
        };
    }
}
