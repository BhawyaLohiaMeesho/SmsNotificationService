package com.notification.sms.controller;

import com.notification.sms.entity.SmsRequest;
import com.notification.sms.exceptions.InvalidApiKeyException;
import com.notification.sms.exceptions.NullRequestBodyException;
import com.notification.sms.request.SendSmsRequest;
import com.notification.sms.request.SmsWithTextRequest;
import com.notification.sms.request.SmsWithinTimeRangeRequest;
import com.notification.sms.response.SendSmsResponse;
import com.notification.sms.response.SmsRequestStatus;
import com.notification.sms.response.SuccessResponse;
import com.notification.sms.service.AuthenticationService;
import com.notification.sms.service.ImiConnectSmsService;
import com.notification.sms.service.ImiConnectSmsServiceImpl;
import com.notification.sms.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/sms")
public class SmsNotificationController {

    ProducerService producerService;
    AuthenticationService authenticationService;

    public SmsNotificationController(ProducerService producerService,AuthenticationService authenticationService){
        this.authenticationService=authenticationService;
        this.producerService=producerService;
    }

    @PostMapping("/send")
    public SuccessResponse <SendSmsResponse> sendSms(@RequestHeader(value = "key",required = false) String apiKey,
                                                     @RequestBody SendSmsRequest smsRequestRequest) throws Exception {

            if(!authenticationService.isAuthorized(apiKey)){
                throw new InvalidApiKeyException();
            }

            if(smsRequestRequest==null){
                   throw new NullRequestBodyException();
            }

           smsRequestRequest.checkRequiredValues();

           SmsRequest smsRequest=new SmsRequest(smsRequestRequest.getPhoneNumber(),smsRequestRequest.getMessage());

           Integer requestId=producerService.sendSms(smsRequest);

           return new SuccessResponse<>(new SendSmsResponse(requestId,"Message queued"));
    }

    @GetMapping("/{requestId}")
    public SuccessResponse<SmsRequest> getSms(@RequestHeader(value = "key",required = false) String apiKey,
                                              @PathVariable("requestId") Integer requestId) throws Exception{

        if(!authenticationService.isAuthorized(apiKey)){
            throw new InvalidApiKeyException();
        }

        if(requestId==null){
            throw new NullRequestBodyException();
        }

        SmsRequest smsRequest=producerService.getSms(requestId);

        return new SuccessResponse<>(smsRequest);
    }

    @PostMapping("/get-within-time-range")
    public SuccessResponse<List<SmsRequest>> getMessagesWithinTimeRange(@RequestHeader(value = "key",required = false) String apiKey,
                                                                        @RequestBody SmsWithinTimeRangeRequest smsWithinTimeRangeRequest) throws Exception {

        if(!authenticationService.isAuthorized(apiKey)){
            throw new InvalidApiKeyException();
        }

        if(smsWithinTimeRangeRequest==null){
            throw new NullRequestBodyException();
        }

        smsWithinTimeRangeRequest.checkRequiredValues();

        List<SmsRequest> smsRequests=producerService.getMessagesWithinTimeRange(smsWithinTimeRangeRequest.getStartTime(),smsWithinTimeRangeRequest.getEndTime(),
                smsWithinTimeRangeRequest.getPageNumber(),smsWithinTimeRangeRequest.getPageSize());

        return new SuccessResponse<>(smsRequests);
    }

    @PostMapping("/get-with-text")
    public SuccessResponse<List<SmsRequest>> getMessagesWithText(@RequestHeader(value = "key",required = false) String apiKey,
                                                                 @RequestBody SmsWithTextRequest smsWithTextRequest) throws Exception {

        if(!authenticationService.isAuthorized(apiKey)){
            throw new InvalidApiKeyException();
        }

        if(smsWithTextRequest==null){
            throw new NullRequestBodyException();
        }

        smsWithTextRequest.checkRequiredValues();

        List <SmsRequest> messagesWithText=producerService.getMessagesWithText(smsWithTextRequest.getText(),
                smsWithTextRequest.getPageNumber(),smsWithTextRequest.getPageSize());

        return new SuccessResponse<>(messagesWithText);
    }

    @PostMapping("/check-imiconnect")
    public SmsRequestStatus checkImi(@RequestBody SmsRequest smsRequest) throws Exception {
        System.out.println(smsRequest);
        ImiConnectSmsService s= new ImiConnectSmsServiceImpl();
        SmsRequestStatus ans=s.sendSms(smsRequest);
        return ans;
    }

}
