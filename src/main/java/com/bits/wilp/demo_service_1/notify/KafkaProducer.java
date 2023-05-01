package com.bits.wilp.demo_service_1.notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void notifyCustomer(Integer id) {
        String message = "Notification request for customer account creation with Customer ID: " + id;
        kafkaTemplate.send("notifyCustomerCreation", message);
        log.info("Sent: {}", message);
    }
}