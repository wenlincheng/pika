package com.wenlincheng.pika.common.core.json;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Jackson 序列化配置
 *
 * @author : Pikaman
 * @version : 1.0.0
 * @date : 2021/1/31 2:10 下午
 */
@Configuration
public class JacksonSerializerConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        Map<Class<?>, JsonSerializer<?>> map = new HashMap<>();
        map.put(LocalDateTime.class, localDateTimeSerializer());
        map.put(Long.class, ToStringSerializer.instance);
        map.put(BigDecimal.class, bigDecimalSerialize());

        return builder -> builder.serializersByType(map);
    }

    @Bean
    public LocalDateTimeSerializer localDateTimeSerializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Bean
    public BigDecimalSerialize bigDecimalSerialize() {
        return new BigDecimalSerialize();
    }

}
