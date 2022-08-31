package com.notification.sms.service;

import com.notification.sms.entity.SmsRequest;
import com.notification.sms.response.SmsRequestStatus;

public interface ImiConnectSmsService {
    public SmsRequestStatus sendSms(SmsRequest smsRequest) throws Exception;
}
