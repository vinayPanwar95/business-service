package com.learning.businessservice.event;

import com.learning.businessservice.entity.OrderEntity;
//import com.learning.businessservice.kafka.service.KafkaProducerService;
import com.learning.businessservice.kafka.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventListener {

    private final KafkaProducerService kafkaProducerService;

    @Value("${application.configs.topic.name : order}")
    private String TOPIC_NAME;

    @EventListener
    public void onOrderEvent(OrderEntity order){
        log.info("Event received to send to kafka : {} ", order);
        kafkaProducerService.sendMsgViaKafkaTemplate("user" , order.getOrderId().toString(), order.toString());
    }
}
