package com.notification.sms.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.notification.sms.constant.Data;
import com.notification.sms.entity.SmsRequest;
import com.notification.sms.request.imiconnectrequest.ChannelDetails;
import com.notification.sms.request.imiconnectrequest.DestinationDetails;
import com.notification.sms.request.imiconnectrequest.SmsDetails;
import com.notification.sms.request.imiconnectrequest.SmsRequestToImiConnect;
import com.notification.sms.response.SmsRequestStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ImiConnectSmsServiceImpl implements ImiConnectSmsService {
    @Override
    public SmsRequestStatus sendSms(SmsRequest smsRequest) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> header = getHeader();

        SmsRequestToImiConnect requestBody = getRequestBody(smsRequest);

        HttpEntity<SmsRequestToImiConnect> entity = new HttpEntity<>(requestBody, header);

        ResponseEntity<String> response = restTemplate.postForEntity(Data.IMICONNECT_URL, entity, String.class);

        SmsRequestStatus smsRequestStatus=getStatusObjectFromResponse(response);

        return smsRequestStatus;
    }
    private MultiValueMap <String,String> getHeader(){
        MultiValueMap <String,String> header=new LinkedMultiValueMap<>();
        header.add("Accept", "application/json");
        header.add("Content-Type", "application/json");
        header.add("key", Data.IMICONNECT_API_KEY);
        return header;
    }

    private SmsRequestStatus getStatusObjectFromResponse(ResponseEntity <String> response) throws Exception {
        if(response==null){
            throw new Exception("Internal server error from 3rd party. Response is null");
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response.getBody());
        String smsRequestStatusString=jsonNode.get("response").toString();
        SmsRequestStatus smsRequestStatus = mapper.readValue(smsRequestStatusString, SmsRequestStatus.class);
        return smsRequestStatus;
    }
    private SmsRequestToImiConnect getRequestBody(SmsRequest smsRequest) throws Exception{

        if(smsRequest==null){
            throw new Exception("Sms request is null");
        }
        String message=smsRequest.getMessage();

        String phoneNumber=smsRequest.getPhoneNumber();

        List<String> msisdn=new ArrayList<>();
        msisdn.add(phoneNumber);
        List <DestinationDetails> destination =new ArrayList<>();
        destination.add(new DestinationDetails(msisdn));
        SmsDetails sms=new SmsDetails(message);
        ChannelDetails channels=new ChannelDetails(sms);
        SmsRequestToImiConnect smsRequestToImiConnect=new SmsRequestToImiConnect("sms",channels,destination);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(smsRequestToImiConnect);
        System.out.println(json);
        return smsRequestToImiConnect;
    }
}
