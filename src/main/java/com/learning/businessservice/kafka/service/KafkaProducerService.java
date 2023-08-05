package com.learning.businessservice.kafka.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {
   private final StreamBridge streamBridge;

    @SneakyThrows
    public void sendMsgViaKafkaTemplate(String topic, String key, String value) {
        log.info(String.format("Producer Message- Key: %s, Value: %s to topic: %s", key, value, topic));
        streamBridge.send("producer-out-0", value);
    }
}
