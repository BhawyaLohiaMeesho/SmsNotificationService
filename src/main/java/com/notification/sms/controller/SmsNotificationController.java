package com.notification.sms.controller;

import com.notification.sms.entity.SmsRequest;
import com.notification.sms.request.SendSmsRequest;
import com.notification.sms.request.SmsWithTextRequest;
import com.notification.sms.request.SmsWithinTimeRangeRequest;
import com.notification.sms.response.SendSmsResponse;
import com.notification.sms.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/sms")
public class SmsNotificationController {

    @Autowired
    ProducerService producerService;

    @PostMapping("/send")
    public SendSmsResponse sendSms(@RequestBody SendSmsRequest smsRequestRequest) throws Exception {
           if(smsRequestRequest==null){
               throw new Exception("BAD_REQUEST");
           }
           SmsRequest smsRequest=new SmsRequest(smsRequestRequest.getPhoneNumber(),smsRequestRequest.getMessage());
           Integer requestId=producerService.sendSms(smsRequest);
           return new SendSmsResponse(requestId,"Request created successfully");
    }

    @GetMapping("/{requestId}")
    public SmsRequest getSms(@PathVariable("requestId") Integer requestId) throws Exception{
        if(requestId==null){
            throw new Exception("BAD_REQUEST");
        }
        System.out.println(requestId);
        return producerService.getSms(requestId);
    }

    @PostMapping("/get-within-time-range")
    public List<SmsRequest> getMessagesWithinTimeRange(@RequestBody SmsWithinTimeRangeRequest smsWithinTimeRangeRequest) throws Exception {

        List<SmsRequest> smsRequests=producerService.getMessagesWithinTimeRange(smsWithinTimeRangeRequest.getStartTime(),smsWithinTimeRangeRequest.getEndTime(),
                smsWithinTimeRangeRequest.getPageNumber(),smsWithinTimeRangeRequest.getPageSize());
        return smsRequests;
    }

    @PostMapping("/get-with-text")
    public List<SmsRequest> getMessagesWithText(@RequestBody SmsWithTextRequest smsWithTextRequest) throws Exception {
        if(smsWithTextRequest==null){
            throw new Exception("Bad request");
        }
        List <SmsRequest> messagesWithText=producerService.getMessagesWithText(smsWithTextRequest.getText(),
                smsWithTextRequest.getPageNumber(),smsWithTextRequest.getPageSize());
        return messagesWithText;
    }

}
