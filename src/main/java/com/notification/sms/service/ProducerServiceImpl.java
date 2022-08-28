package com.notification.sms.service;

import com.notification.sms.constant.Data;
import com.notification.sms.dao.kafka.KafkaProducerDao;
import com.notification.sms.dao.mysql.BlacklistDao;
import com.notification.sms.dao.mysql.SmsRequestDao;
import com.notification.sms.dao.redis.RedisBlacklistDao;
import com.notification.sms.entity.PhoneNumber;
import com.notification.sms.entity.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProducerServiceImpl implements ProducerService {

    SmsRequestDao smsRequestDao;
    KafkaProducerDao kafkaProducerDao;

    BlacklistDao blacklistDao;

    RedisBlacklistDao redisBlacklistDao;

    @Autowired
    public ProducerServiceImpl(SmsRequestDao smsRequestDao,KafkaProducerDao kafkaProducerDao,
                               BlacklistDao blacklistDao,RedisBlacklistDao redisBlacklistDao){
        this.smsRequestDao=smsRequestDao;
        this.kafkaProducerDao=kafkaProducerDao;
        this.blacklistDao=blacklistDao;
        this.redisBlacklistDao=redisBlacklistDao;
    }

    @Override
    public SmsRequest getSms(Integer requestId) throws Exception {
        Optional<SmsRequest> smsRequest=smsRequestDao.findById(requestId);
        if(smsRequest.isPresent()){
            return smsRequest.get();
        }
        throw new Exception("Element not found");
    }

    @Override
    public Integer sendSms(SmsRequest smsRequest) throws Exception {
        SmsRequest savedSmsRequest=smsRequestDao.save(smsRequest);
        Integer requestId=savedSmsRequest.getId();
        //kafkaProducerDao.sendSmsRequest(Data.TOPIC_SMS_REQUEST,requestId);
        return requestId;
    }

    @Override
    public void addToBlacklist(List<PhoneNumber> phoneNumberList) throws Exception {
         List <PhoneNumber> savedPhoneNumberList=blacklistDao.saveAll(phoneNumberList);
         redisBlacklistDao.saveAll(phoneNumberList);
    }

    @Override
    public void removeFromBlacklist(List<PhoneNumber> phoneNumberList) throws Exception {
        blacklistDao.deleteAll(phoneNumberList);
        redisBlacklistDao.deleteAll(phoneNumberList);
    }

    @Override
    public List<PhoneNumber> getBlacklist() throws Exception {
        List <PhoneNumber> blacklist=blacklistDao.findAll();
        List <PhoneNumber> blacklist2=redisBlacklistDao.getAll();
        return blacklist2;
    }

}
