package com.wgs.ddd.v2.messaging;

import com.wgs.ddd.v2.domain.AuditMessage;

public interface MessageSender {
    boolean sender(AuditMessage msg);
}
