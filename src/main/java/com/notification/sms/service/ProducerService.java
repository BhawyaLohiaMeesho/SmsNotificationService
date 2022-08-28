package com.notification.sms.service;

import com.notification.sms.entity.PhoneNumber;
import com.notification.sms.entity.SmsRequest;
import com.notification.sms.request.SendSmsRequest;

import java.util.List;

public interface ProducerService {
    public Integer sendSms(SmsRequest smsRequest) throws Exception;

    public SmsRequest getSms(Integer requestId) throws Exception;

    public void addToBlacklist(List<PhoneNumber> phoneNumberList) throws Exception;

    public void removeFromBlacklist(List <PhoneNumber> phoneNumberList) throws Exception;

    public List<PhoneNumber> getBlacklist() throws Exception;
}
