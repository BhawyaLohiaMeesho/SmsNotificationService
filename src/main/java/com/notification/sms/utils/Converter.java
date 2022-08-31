package com.notification.sms.utils;

import com.notification.sms.constant.Data;
import com.notification.sms.entity.PhoneNumber;
import com.notification.sms.entity.SmsRequest;
import com.notification.sms.response.SmsRequestStatus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Converter {
    public static List <PhoneNumber> convertToPhoneNumberType(List<String> list) throws Exception{

          if(list==null){
              throw new Exception("Null input provided");
          }
          List <PhoneNumber> phoneNumbersList = new ArrayList<>();
        for (String string : list) {
            PhoneNumber phoneNumber = new PhoneNumber(string);
            phoneNumbersList.add(phoneNumber);
        }
          return phoneNumbersList;
    }
    public static List <PhoneNumber> convertToPhoneNumberType(Set<String> set) throws Exception{

        if(set==null){
            throw new Exception("Null input provided");
        }
        List <PhoneNumber> phoneNumbersList = new ArrayList<>();
        for (String string : set) {
            PhoneNumber phoneNumber = new PhoneNumber(string);
            phoneNumbersList.add(phoneNumber);
        }
        return phoneNumbersList;
    }
    public static SmsRequest getSmsRequestWithUpdatedStatus(SmsRequest smsRequest, SmsRequestStatus smsRequestStatus) throws Exception{
        if(smsRequestStatus==null)
            throw new Exception("Sms request status is null");
        smsRequest.setStatus(smsRequestStatus.getStatus());
        if(!smsRequestStatus.getStatus().equals(Data.IMICONNECT_SUCCESS_CODE)) {
            smsRequest.setFailureCode(smsRequestStatus.getCode());
            smsRequest.setFailureComments(smsRequestStatus.getComments());
        }
        return smsRequest;
    }

}
