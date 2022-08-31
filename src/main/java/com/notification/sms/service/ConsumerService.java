package com.notification.sms.service;

public interface ConsumerService {
    public void listenerForSmsRequest(Integer requestId) throws Exception;
}
