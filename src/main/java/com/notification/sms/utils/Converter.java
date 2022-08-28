package com.notification.sms.utils;

import com.notification.sms.entity.PhoneNumber;

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
        for (String s : list) {
            PhoneNumber phoneNumber = new PhoneNumber(s);
            phoneNumbersList.add(phoneNumber);
        }
          return phoneNumbersList;
    }
    public static List <PhoneNumber> convertToPhoneNumberType(Set<String> set) throws Exception{
        if(set==null){
            throw new Exception("Null input provided");
        }
        List <PhoneNumber> phoneNumbersList = new ArrayList<>();
        for (String s : set) {
            PhoneNumber phoneNumber = new PhoneNumber(s);
            phoneNumbersList.add(phoneNumber);
        }
        return phoneNumbersList;
    }

}
