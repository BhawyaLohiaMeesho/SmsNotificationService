package com.notification.sms.request.imiconnectrequest;

public class SmsDetails {
    private String text;

    public SmsDetails(){}

    public SmsDetails(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "SmsDetails{" +
                "text='" + text + '\'' +
                '}';
    }
}
