package com.notification.sms.dao.kafka;

public interface KafkaProducerDao {
    public void sendSmsRequest(String topicName,Integer requestId);
}
