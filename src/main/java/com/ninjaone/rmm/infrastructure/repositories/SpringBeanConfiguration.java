package com.ninjaone.rmm.infrastructure.repositories;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

/**
 * Bean configuration class to reuse components in application
 */
@Configuration
public class SpringBeanConfiguration {
    @Bean
    public ObjectMapper nubankObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    public JPAQueryFactory queryFactory(EntityManager entityManager){
        return new JPAQueryFactory(entityManager);
    }
}
