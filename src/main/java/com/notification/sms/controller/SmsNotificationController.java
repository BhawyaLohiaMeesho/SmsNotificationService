package com.notification.sms.controller;

import com.notification.sms.entity.SmsRequest;
import com.notification.sms.request.SendSmsRequest;
import com.notification.sms.response.SendSmsResponse;
import com.notification.sms.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
