package com.wgs.ddd;

import com.wgs.ddd.messaging.MessageProducer;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer implements MessageProducer {

    private KafkaTemplate kafkaTemplate;

    @Override
    public boolean send(String key, String content) {
        kafkaTemplate.send(key, content);
        return true;
    }
}
