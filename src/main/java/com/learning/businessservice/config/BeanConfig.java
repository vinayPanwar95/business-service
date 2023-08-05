package com.learning.businessservice.config;

import com.learning.common.datetime.DateTimerHelper;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Configuration
public class BeanConfig {
    @ServiceActivator(inputChannel = "order.metadata")
    public void handler(RecordMetadata recordMetadata){
        if(recordMetadata != null){
            System.err.println("recordMetadata "+ recordMetadata);
            System.err.println("topic "+ recordMetadata.topic());
            System.err.println("partition "+ recordMetadata.partition());
            System.err.println("OffSet "+ recordMetadata.offset());
        }
        else{
            System.err.println("RecordMetadata is null");
        }

    }
}
