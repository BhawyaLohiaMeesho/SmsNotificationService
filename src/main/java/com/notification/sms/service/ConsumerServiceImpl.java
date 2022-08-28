package com.notification.sms.service;

import com.notification.sms.constant.Data;
import com.notification.sms.entity.SmsRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Override
    //@KafkaListener(topics= Data.TOPIC_SMS_REQUEST,groupId = "sms_request_handlers")
    public void listenerForSmsRequest(Integer requestId) {
            System.out.println("Request to consumer: "+requestId);

    }
}
