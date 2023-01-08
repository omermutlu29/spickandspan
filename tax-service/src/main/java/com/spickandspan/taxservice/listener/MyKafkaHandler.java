package com.spickandspan.taxservice.listener;

import com.spickandspan.taxservice.event.ProductEvent;
import com.spickandspan.taxservice.event.ProductTestEvent;
import com.spickandspan.taxservice.service.TaxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@KafkaListener(groupId = "testgroupid", topics = "productActionTopic")
public class MyKafkaHandler {

    @Autowired
    private TaxService taxService;

    @KafkaHandler
    void test(String key, String value){
        System.out.println(key);
    }
    @KafkaHandler
    void handleProductCreatedEvent(ProductEvent productCreatedEvent) {
        System.out.println(productCreatedEvent);
       // taxService.handleEventService(productCreatedEvent);
    }

    @KafkaHandler
    void handleProductCreatedEvent(ProductTestEvent productTestEvent) {
        System.out.println(productTestEvent);
        // taxService.handleEventService(productCreatedEvent);
    }


    @KafkaHandler(isDefault = true)
    void handleDefault(@Payload Object unknown,
                       @Header(KafkaHeaders.OFFSET) long offset,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partitionId,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("Server received unknown message {},{},{}", offset, partitionId, topic);
    }
}