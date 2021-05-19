package com.wgs.ddd.v2.messaging;

import com.wgs.ddd.v2.domain.AuditMessage;
import org.springframework.beans.factory.annotation.Autowired;

public class KafkaSender implements MessageSender {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public boolean sender(AuditMessage msg) {
        String content = msg.serialize();
        // kafkaTemplate.send(key, content);
        return true;
    }
}
