package com.notification.sms.service;

public interface AuthenticationService {

    public boolean isAuthorized(String apiKey) throws Exception;

    public String generateNewKey();
}
