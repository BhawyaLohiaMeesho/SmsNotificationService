package com.notification.sms.request.imiconnectrequest;

import java.util.List;

public class DestinationDetails {
    private List<String> msisdn;

    public DestinationDetails(){}

    public DestinationDetails(List<String> msisdn) {
        this.msisdn = msisdn;
    }

    public List<String> getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(List<String> msisdn) {
        this.msisdn = msisdn;
    }

    @Override
    public String toString() {
        return "DestinationDetails{" +
                "msisdn=" + msisdn +
                '}';
    }
}
