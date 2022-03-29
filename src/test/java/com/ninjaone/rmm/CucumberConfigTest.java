package com.ninjaone.rmm;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Configuration of Cucumber testing for run test as Junit 5
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features",
        publish = true,
        plugin = {"pretty", "html:build/cucumber/report.html"},
        glue = "com.ninjaone.rmm")
public class CucumberConfigTest {
}
