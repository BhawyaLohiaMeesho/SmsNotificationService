package com.notification.sms.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.sms.constant.Data;
import com.notification.sms.entity.SmsRequest;
import com.notification.sms.response.SmsRequestStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
@Service
public class ImiConnectSmsServiceImpl implements ImiConnectSmsService {
    @Override
    public SmsRequestStatus sendSms(SmsRequest smsRequest) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> header = getHeader();

        String requestBody = getRequestBody(smsRequest);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, header);

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
    private String getRequestBody(SmsRequest smsRequest) throws Exception{
        if(smsRequest==null){
            throw new Exception("Sms request is null");
        }
        return "{\"deliverychannel\": \"sms\",\"channels\": {\"sms\": {\"text\": \"Hello, Greetings from Meesho.Click here to know more about us:https://meesho.com\"}},\"destination\": [{\"msisdn\": [\"+918953235585\"]}]}";
    }
}
