package com.notification.sms.service;

import com.notification.sms.exceptions.ApiKeyNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    HashSet <String> apiKeyStore;

    @Autowired
    public AuthenticationServiceImpl(){
        apiKeyStore = new HashSet<>();
        apiKeyStore.add("yek");
    }

    public boolean isAuthorized(String apiKey) throws Exception{
        if(apiKey==null)
            throw new ApiKeyNullException();
        return apiKeyStore.contains(apiKey);
    }

    public String generateNewKey(){
        return "";
    }
}
