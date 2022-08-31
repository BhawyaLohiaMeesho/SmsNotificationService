package com.notification.sms.request.imiconnectrequest;

public class ChannelDetails {
    private SmsDetails sms;

    public ChannelDetails(){}
    public ChannelDetails(SmsDetails sms) {
        this.sms = sms;
    }

    public SmsDetails getSms() {
        return sms;
    }

    public void setSms(SmsDetails sms) {
        this.sms = sms;
    }

    @Override
    public String toString() {
        return "ChannelDetails{" +
                "sms=" + sms +
                '}';
    }
}
