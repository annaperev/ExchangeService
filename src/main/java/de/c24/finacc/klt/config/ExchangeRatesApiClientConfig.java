package de.c24.finacc.klt.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.c24.finacc.klt.service.ExchangeRatesApiErrorDecoder;
import feign.Contract;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;

public class ExchangeRatesApiClientConfig {
    @Bean
    public Decoder decoder() {
        return new JacksonDecoder(ExchangeRatesApiObjectMapper());
    }

    @Bean
    public Encoder encoder() {
        return new JacksonEncoder(ExchangeRatesApiObjectMapper());
    }

    @Bean
    public Contract feignContract() {
        return new Contract.Default();
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new ExchangeRatesApiErrorDecoder(ExchangeRatesApiObjectMapper());
    }

    public ObjectMapper ExchangeRatesApiObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true);
        objectMapper.registerModules(new Jdk8Module(), new JavaTimeModule());
        return objectMapper;
    }
}
