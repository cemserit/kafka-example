package com.cemserit.kafka.consumer;

import com.cemserit.kafka.consumer.service.KafkaLogListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkaConsumerApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(KafkaConsumerApplication.class, args);

        KafkaLogListener kafkaLogListener = context.getBean(KafkaLogListener.class);
    }
}
