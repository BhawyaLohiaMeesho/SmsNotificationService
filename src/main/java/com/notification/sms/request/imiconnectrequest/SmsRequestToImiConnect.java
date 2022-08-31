package com.notification.sms.request.imiconnectrequest;

import java.util.List;

public class SmsRequestToImiConnect {
    private String deliverychannel;
    private ChannelDetails channels;
    private List<DestinationDetails> destination;

    public SmsRequestToImiConnect(){}
    public SmsRequestToImiConnect(String deliverychannel, ChannelDetails channels, List<DestinationDetails> destination) {
        this.deliverychannel = deliverychannel;
        this.channels = channels;
        this.destination = destination;
    }

    public String getDeliverychannel() {
        return deliverychannel;
    }

    public void setDeliverychannel(String deliverychannel) {
        this.deliverychannel = deliverychannel;
    }

    public ChannelDetails getChannels() {
        return channels;
    }

    public void setChannels(ChannelDetails channels) {
        this.channels = channels;
    }

    public List<DestinationDetails> getDestination() {
        return destination;
    }

    public void setDestination(List<DestinationDetails> destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "SmsRequestToImiConnect{" +
                "deliverychannel='" + deliverychannel + '\'' +
                ", channels=" + channels +
                ", destination=" + destination +
                '}';
    }
}
