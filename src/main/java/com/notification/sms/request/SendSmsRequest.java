package com.notification.sms.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.notification.sms.entity.PhoneNumber;
import com.notification.sms.exceptions.InvalidPhoneNumberException;
import com.notification.sms.exceptions.NullMessageException;
import com.notification.sms.exceptions.NullPhoneNumberException;
import com.notification.sms.response.SendSmsResponse;

public class SendSmsRequest {
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String message;

    public SendSmsRequest(){}

    public SendSmsRequest(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() { return message; }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void checkRequiredValues() throws Exception{
          if(phoneNumber==null)
              throw new NullPhoneNumberException();
          if(message==null)
              throw new NullMessageException();
          if(!PhoneNumber.isValid(phoneNumber)){
              throw new InvalidPhoneNumberException();
          }
    }

    @Override
    public String toString() {
        return "Sms{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
