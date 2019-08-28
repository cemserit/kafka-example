package com.cemserit.kafka.producer.callback;

import com.cemserit.kafka.core.model.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Created by cemserit on 27.08.2019.
 */
public class KafkaLogCallbackListener implements ListenableFutureCallback<SendResult<String, Log>> {
    private Logger logger = LoggerFactory.getLogger(KafkaLogCallbackListener.class);

    @Override
    public void onFailure(Throwable ex) {
        logger.info("Kafka log stream fail!", ex);
    }

    @Override
    public void onSuccess(SendResult<String, Log> result) {
        logger.info("Kafka log stream success, {}", result);
    }
}