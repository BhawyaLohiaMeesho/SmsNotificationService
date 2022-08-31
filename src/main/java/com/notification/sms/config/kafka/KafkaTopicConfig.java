package com.notification.sms.config.kafka;

import com.notification.sms.constant.Data;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, Data.KAFKA_SERVER_ADDRESS);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topicSmsRequest() {
        return new NewTopic(Data.TOPIC_SMS_REQUEST, 1, (short) 1);
    }
}
