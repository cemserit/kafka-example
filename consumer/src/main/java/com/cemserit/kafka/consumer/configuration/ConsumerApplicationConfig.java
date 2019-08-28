package com.cemserit.kafka.consumer.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Created by cemserit on 27.08.2019.
 */
@Configuration
@ComponentScan(basePackages = "com.cemserit.kafka")
@EnableKafka
public class ConsumerApplicationConfig {
}
