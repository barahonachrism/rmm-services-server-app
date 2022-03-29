package com.ninjaone.rmm;

import io.cucumber.java.ParameterType;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Configuration of Cucumber testing integrated with Spring Boot
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = NinjaOneRmmApplication.class)
@CucumberContextConfiguration
public class CucumberSpringConfiguration {
    @ParameterType("true|false")
    public boolean booleanValue(String value){
        return Boolean.valueOf(value);
    }
}
