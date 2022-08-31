package com.notification.sms.controller;

import com.notification.sms.entity.PhoneNumber;
import com.notification.sms.exceptions.InvalidApiKeyException;
import com.notification.sms.exceptions.NullRequestBodyException;
import com.notification.sms.request.BlacklistRequest;
import com.notification.sms.response.SuccessResponse;
import com.notification.sms.service.AuthenticationService;
import com.notification.sms.service.AuthenticationServiceImpl;
import com.notification.sms.service.ProducerService;
import com.notification.sms.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.notification.sms.utils.Converter;

@RestController
@RequestMapping("/v1/blacklist")
public class BlacklistController {

     ProducerService producerService;

     AuthenticationService authenticationService;

     @Autowired
     public BlacklistController(ProducerService producerService, AuthenticationService authenticationService){
         this.producerService=producerService;
         this.authenticationService=authenticationService;
     }

     @GetMapping("/")
     public SuccessResponse<List<PhoneNumber>> getBlacklist(@RequestHeader(value = "key",required = false) String apiKey) throws Exception{

         if(!authenticationService.isAuthorized(apiKey)){
             throw new InvalidApiKeyException();
         }

         List <PhoneNumber> blacklist=producerService.getBlacklist();

         return new SuccessResponse<>(blacklist);
     }

     @PostMapping("/add")
     public SuccessResponse<String> addToBlacklist(@RequestHeader(value = "key",required = false) String apiKey,
                                                   @RequestBody BlacklistRequest blacklistRequest) throws Exception{

         if(!authenticationService.isAuthorized(apiKey)){
             throw new InvalidApiKeyException();
         }

         if(blacklistRequest==null){
            throw new NullRequestBodyException();
         }

        blacklistRequest.checkRequiredValues();

        List <PhoneNumber> phoneNumberList=Converter.convertToPhoneNumberType(blacklistRequest.getPhoneNumbers());

        producerService.addToBlacklist(phoneNumberList);

        return new SuccessResponse<>("Added Successfully");
     }

     @PostMapping("/delete")
     public SuccessResponse <String> removeFromBlacklist(@RequestHeader(value = "key",required = false) String apiKey,
                                                         @RequestBody BlacklistRequest blacklistRequest) throws Exception{

         if(!authenticationService.isAuthorized(apiKey)){
             throw new InvalidApiKeyException();
         }

         if(blacklistRequest==null){
             throw new NullRequestBodyException();
         }

         blacklistRequest.checkRequiredValues();

         List <PhoneNumber> phoneNumberList=Converter.convertToPhoneNumberType(blacklistRequest.getPhoneNumbers());

         producerService.removeFromBlacklist(phoneNumberList);

         return new SuccessResponse<>("Removed Successfully");
     }
}
