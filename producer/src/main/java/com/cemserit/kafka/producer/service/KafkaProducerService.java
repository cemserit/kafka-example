package com.cemserit.kafka.producer.service;

import com.cemserit.kafka.core.model.Log;
import com.cemserit.kafka.core.model.Message;
import com.cemserit.kafka.producer.callback.KafkaLogCallbackListener;
import com.cemserit.kafka.producer.callback.KafkaMessageCallbackListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;

/**
 * Created by cemserit on 27.08.2019.
 */
@Service
public class KafkaProducerService implements ProducerService {

    private final KafkaTemplate<String, Message> messageKafkaTemplate;
    private final KafkaTemplate<String, Log> logKafkaTemplate;
    private final KafkaLogCallbackListener logCallbackListener;
    private final KafkaMessageCallbackListener messageCallbackListener;
    @Value(value = "${kafka.topic.message.topicName}")
    private String messageTopicName;
    @Value(value = "${kafka.topic.log.topicName}")
    private String logTopicName;
    @Value(value = "${kafka.topic.message.partition}")
    private int messageTopicPartition;
    @Value(value = "${kafka.topic.log.partition}")
    private int logTopicPartition;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, Message> messageKafkaTemplate,
                                KafkaTemplate<String, Log> logKafkaTemplate) {
        this.messageKafkaTemplate = messageKafkaTemplate;
        this.logKafkaTemplate = logKafkaTemplate;
        this.logCallbackListener = new KafkaLogCallbackListener();
        this.messageCallbackListener = new KafkaMessageCallbackListener();
    }

    // send dummy log data every second
    @Scheduled(fixedDelay = 1000L * 1, initialDelay = 1000L)
    public void sendRandomLogData() {
        UUID key = UUID.randomUUID();
        String rawLog = UUID.randomUUID().toString();
        long timestamp = System.currentTimeMillis();
        Log log = new Log(key, rawLog, timestamp);
        sendLog(log);
    }

    // send dummy message data every second
    @Scheduled(fixedDelay = 1000L * 1, initialDelay = 1000L * 3)
    public void sendRandomMessageData() {
        UUID key = UUID.randomUUID();
        String randomStr = UUID.randomUUID().toString();
        long timestamp = System.currentTimeMillis();
        Message message = new Message(key, randomStr, timestamp, "@cemserit");
        sendMessage(message);
    }

    @Override
    public void sendMessage(Message message) {
        String key = message.getKey().toString();
        int partition = Math.abs(key.hashCode()) % messageTopicPartition;

        ListenableFuture<SendResult<String, Message>> messageTopic = messageKafkaTemplate.send(messageTopicName,
                partition, key, message);
        messageTopic.addCallback(messageCallbackListener);
    }

    @Override
    public void sendLog(Log log) {
        String key = log.getKey().toString();
        int partition = Math.abs(key.hashCode()) % logTopicPartition;
        ListenableFuture<SendResult<String, Log>> logsTopic = logKafkaTemplate.send(logTopicName, partition, key, log);
        logsTopic.addCallback(logCallbackListener);
    }
}
