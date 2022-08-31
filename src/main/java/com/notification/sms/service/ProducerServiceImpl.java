package com.notification.sms.service;

import com.notification.sms.constant.Data;
import com.notification.sms.dao.elasticsearch.ElasticsearchSmsRequestDao;
import com.notification.sms.dao.kafka.KafkaProducerDao;
import com.notification.sms.dao.mysql.BlacklistDao;
import com.notification.sms.dao.mysql.SmsRequestDao;
import com.notification.sms.dao.redis.RedisBlacklistDao;
import com.notification.sms.entity.PhoneNumber;
import com.notification.sms.entity.SmsRequest;
import com.notification.sms.request.SmsWithTextRequest;
import com.notification.sms.request.SmsWithinTimeRangeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProducerServiceImpl implements ProducerService {

    SmsRequestDao smsRequestDao;
    KafkaProducerDao kafkaProducerDao;

    BlacklistDao blacklistDao;

    RedisBlacklistDao redisBlacklistDao;

    ElasticsearchSmsRequestDao elasticsearchSmsRequestDao;

    @Autowired
    public ProducerServiceImpl(SmsRequestDao smsRequestDao,KafkaProducerDao kafkaProducerDao,
                               BlacklistDao blacklistDao,RedisBlacklistDao redisBlacklistDao,
                               ElasticsearchSmsRequestDao elasticsearchSmsRequestDao){
        this.smsRequestDao=smsRequestDao;
        this.kafkaProducerDao=kafkaProducerDao;
        this.blacklistDao=blacklistDao;
        this.redisBlacklistDao=redisBlacklistDao;
        this.elasticsearchSmsRequestDao=elasticsearchSmsRequestDao;
    }

    @Override
    public SmsRequest getSms(Integer requestId) throws Exception {
        Optional<SmsRequest> smsRequest=smsRequestDao.findById(requestId);
        if(!smsRequest.isPresent()){
            throw new Exception("Element not found");
        }
        return smsRequest.get();
    }

    @Override
    public Integer sendSms(SmsRequest smsRequest) throws Exception {
            SmsRequest savedSmsRequest = smsRequestDao.save(smsRequest);
            Integer requestId = savedSmsRequest.getId();
            kafkaProducerDao.sendSmsRequest(Data.TOPIC_SMS_REQUEST,requestId);
            elasticsearchSmsRequestDao.save(savedSmsRequest);
            return requestId;
    }

    @Override
    public void addToBlacklist(List<PhoneNumber> phoneNumberList) throws Exception {
         blacklistDao.saveAll(phoneNumberList);
         redisBlacklistDao.saveAll(phoneNumberList);
    }

    @Override
    public void removeFromBlacklist(List<PhoneNumber> phoneNumberList) throws Exception {
        blacklistDao.deleteAll(phoneNumberList);
        redisBlacklistDao.deleteAll(phoneNumberList);
    }

    @Override
    public List<PhoneNumber> getBlacklist() throws Exception {
        List <PhoneNumber> blacklist=redisBlacklistDao.getAll();
        return blacklist;
    }

    @Override
    public List<SmsRequest> getMessagesWithinTimeRange(LocalDateTime startTime,
                                                       LocalDateTime endTime,
                                                       Integer pageNumber,
                                                       Integer pageSize) throws Exception {
           Page<SmsRequest> resultPage = elasticsearchSmsRequestDao.findByUpdatedAtBetween(startTime, endTime, PageRequest.of(pageNumber, pageSize));
           List <SmsRequest> messagesWithinTimeRange=resultPage.getContent();
           System.out.println(messagesWithinTimeRange);
           return messagesWithinTimeRange;
    }

    @Override
    public List<SmsRequest> getMessagesWithText(String text,
                                                Integer pageNumber,
                                                Integer pageSize) throws Exception {
        List <SmsRequest> messagesWithText=elasticsearchSmsRequestDao.findByMessageContaining(text,PageRequest.of(pageNumber,pageSize));
        return messagesWithText;
    }

}
