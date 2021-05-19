package com.wgs.ddd.messaging;

public interface MessageProducer {

    /**
     * 消息发送
     *
     * @param key     消息key
     * @param content 消息内容
     * @return
     */
    boolean send(String key, String content);
}
