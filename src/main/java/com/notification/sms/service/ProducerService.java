package com.notification.sms.service;

import com.notification.sms.entity.PhoneNumber;
import com.notification.sms.entity.SmsRequest;
import com.notification.sms.request.SendSmsRequest;
import com.notification.sms.request.SmsWithTextRequest;
import com.notification.sms.request.SmsWithinTimeRangeRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

public interface ProducerService {
    public Integer sendSms(SmsRequest smsRequest) throws Exception;

    public SmsRequest getSms(Integer requestId) throws Exception;

    public void addToBlacklist(List<PhoneNumber> phoneNumberList) throws Exception;

    public void removeFromBlacklist(List <PhoneNumber> phoneNumberList) throws Exception;

    public List<PhoneNumber> getBlacklist() throws Exception;

    public List <SmsRequest> getMessagesWithinTimeRange(LocalDateTime startTime,
                                                        LocalDateTime endTime,
                                                        Integer pageNumber, Integer pageSize) throws Exception;

    public List <SmsRequest> getMessagesWithText(String text, Integer pageNumber, Integer pageSize) throws Exception;

}
