package com.notification.sms.dao.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class KafkaProducerDaoImpl implements KafkaProducerDao{
    KafkaTemplate<Integer,Integer> sender;

    @Autowired
    public KafkaProducerDaoImpl(KafkaTemplate<Integer,Integer> sender){
        this.sender=sender;
    }

    public void sendSmsRequest(String topic,Integer requestId){
        ListenableFuture<SendResult<Integer, Integer>> future = sender.send(topic, requestId);

        future.addCallback(new ListenableFutureCallback<SendResult<Integer, Integer>>() {
            @Override
            public void onSuccess(SendResult<Integer, Integer> result) {
                System.out.println("Sent message=[" + requestId +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable exception) {
                System.out.println("Unable to send message=["
                        + requestId + "] due to : " + exception.getMessage());
            }
        });

    }
}
