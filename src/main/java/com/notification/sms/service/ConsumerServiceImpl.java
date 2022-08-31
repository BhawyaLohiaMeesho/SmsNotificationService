package com.notification.sms.service;

import com.notification.sms.constant.Data;
import com.notification.sms.dao.elasticsearch.ElasticsearchSmsRequestDao;
import com.notification.sms.dao.mysql.SmsRequestDao;
import com.notification.sms.dao.redis.RedisBlacklistDao;
import com.notification.sms.entity.PhoneNumber;
import com.notification.sms.entity.SmsRequest;
import com.notification.sms.response.SmsRequestStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    SmsRequestDao smsRequestDao;

    RedisBlacklistDao redisBlacklistDao;

    ImiConnectSmsService imiConnectSmsService;

    ElasticsearchSmsRequestDao elasticsearchSmsRequestDao;

    public ConsumerServiceImpl(SmsRequestDao smsRequestDao, RedisBlacklistDao redisBlacklistDao,
                               ImiConnectSmsService imiConnectSmsService,ElasticsearchSmsRequestDao elasticsearchSmsRequestDao){
        this.imiConnectSmsService=imiConnectSmsService;
        this.redisBlacklistDao=redisBlacklistDao;
        this.smsRequestDao=smsRequestDao;
        this.elasticsearchSmsRequestDao=elasticsearchSmsRequestDao;
    }

    @Override
    @KafkaListener(topics= Data.TOPIC_SMS_REQUEST,groupId = "sms_request_handlers")
    public void listenerForSmsRequest(Integer requestId)  {
           try {
               Optional<SmsRequest> smsRequestDetailsOptional = smsRequestDao.findById(requestId);
               if (!smsRequestDetailsOptional.isPresent())
                   throw new Exception("Request With gived in not found in db");
               SmsRequest smsRequest = smsRequestDetailsOptional.get();
               PhoneNumber currentRequestPhoneNumber = new PhoneNumber(smsRequest.getPhoneNumber());
               boolean isBlacklisted = redisBlacklistDao.isPresent(currentRequestPhoneNumber);
               if (isBlacklisted)
                   return;
               SmsRequestStatus smsRequestStatus = imiConnectSmsService.sendSms(smsRequest);
               SmsRequest smsRequestWithUpdatedStatus = getSmsRequestWithUpdatedStatus(smsRequest, smsRequestStatus);
               SmsRequest smsRequestWithUpdatedUpdateTime = smsRequestDao.save(smsRequestWithUpdatedStatus);
               elasticsearchSmsRequestDao.save(smsRequestWithUpdatedUpdateTime);
               System.out.println(smsRequestWithUpdatedUpdateTime);
           }
           catch(Exception exception){
               System.out.println(exception.getMessage());
           }
    }
    public SmsRequest getSmsRequestWithUpdatedStatus(SmsRequest smsRequest,SmsRequestStatus smsRequestStatus) throws Exception{
        if(smsRequestStatus==null)
            throw new Exception("Sms request status is null");
        smsRequest.setStatus(smsRequestStatus.getStatus());
        if(!smsRequestStatus.getStatus().equals(Data.IMICONNECT_SUCCESS_CODE)) {
            smsRequest.setFailureCode(smsRequestStatus.getCode());
            smsRequest.setFailureComments(smsRequestStatus.getComments());
        }
        return smsRequest;
    }
}
