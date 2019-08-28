package com.cemserit.kafka.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * Created by cemserit on 27.08.2019.
 */
public class Message {
    private UUID key;
    private String message;
    private long timestamp;
    private String createdBy;

    @JsonCreator
    public Message(@JsonProperty("key") UUID key,
                   @JsonProperty("message") String message,
                   @JsonProperty("timestamp") long timestamp,
                   @JsonProperty("createdBy") String createdBy) {
        this.key = key;
        this.message = message;
        this.timestamp = timestamp;
        this.createdBy = createdBy;
    }

    public UUID getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public String toString() {
        return "Message{" +
                "key=" + key +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
