package com.notification.sms.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.notification.sms.entity.PhoneNumber;
import com.notification.sms.exceptions.NullItemPhoneNumberListException;
import com.notification.sms.exceptions.NullPhoneNumberListException;

import java.util.List;

public class BlacklistRequest {
    @JsonProperty("phone_numbers")
    private List<String> phoneNumbers;

    public BlacklistRequest(){}

    public BlacklistRequest(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void checkRequiredValues() throws Exception{
        if(phoneNumbers==null)
            throw new NullPhoneNumberListException();

        for (String phoneNumber : phoneNumbers){
            if(phoneNumber==null){
                throw new NullItemPhoneNumberListException();
            }
        }
    }

    @Override
    public String toString() {
        return "BlacklistRequest:{" +
                "phoneNumbers : " + phoneNumbers +
                '}';
    }
}
