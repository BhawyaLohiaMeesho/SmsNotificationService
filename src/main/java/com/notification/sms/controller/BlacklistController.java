package com.notification.sms.controller;

import com.notification.sms.entity.PhoneNumber;
import com.notification.sms.request.BlacklistRequest;
import com.notification.sms.response.SuccessResponse;
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

     @Autowired
     public BlacklistController(ProducerService producerService){
         this.producerService=producerService;
     }

     @GetMapping("/")
     public SuccessResponse<List<PhoneNumber>> getBlacklist() throws Exception{

         List <PhoneNumber> blacklist=producerService.getBlacklist();

         return new SuccessResponse<>(blacklist);
     }

     @PostMapping("/add")
     public SuccessResponse<String> addToBlacklist(@RequestBody BlacklistRequest blacklistRequest) throws Exception{
        if(blacklistRequest==null){
            throw new Exception("BAD_REQUEST");
        }

        blacklistRequest.checkRequiredValues();

        List <PhoneNumber> phoneNumberList=Converter.convertToPhoneNumberType(blacklistRequest.getPhoneNumbers());

        producerService.addToBlacklist(phoneNumberList);

        return new SuccessResponse<>("Added Successfully");
     }

     @PostMapping("/delete")
     public SuccessResponse <String> removeFromBlacklist(@RequestBody BlacklistRequest blacklistRequest) throws Exception{
         if(blacklistRequest==null){
             throw new Exception("BAD_REQUEST");
         }

         blacklistRequest.checkRequiredValues();

         List <PhoneNumber> phoneNumberList=Converter.convertToPhoneNumberType(blacklistRequest.getPhoneNumbers());

         producerService.removeFromBlacklist(phoneNumberList);

         return new SuccessResponse<>("Removed Successfully");
     }
}
