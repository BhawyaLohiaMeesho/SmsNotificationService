package com.notification.sms.dao.mysql;

import com.notification.sms.entity.SmsRequest;
import com.notification.sms.response.SmsRequestStatus;
import com.notification.sms.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;

@Component
public class SmsRequestDaoImpl {

    EntityManager entityManager;

    @Autowired
    public SmsRequestDaoImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Transactional
    public SmsRequest updateSmsRequestStatus(SmsRequest smsRequest, SmsRequestStatus smsRequestStatus) throws Exception{
        SmsRequest smsRequestWithUpdatedStatus=Converter.getSmsRequestWithUpdatedStatus(smsRequest,smsRequestStatus);
        SmsRequest result=entityManager.merge(smsRequestWithUpdatedStatus);
        return result;
    }
}
