package com.notification.sms.dao.redis;

import com.notification.sms.entity.PhoneNumber;
import com.notification.sms.exceptions.NullItemPhoneNumberListException;
import com.notification.sms.exceptions.NullPhoneNumberException;
import com.notification.sms.exceptions.NullPhoneNumberListException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import java.util.List;
import java.util.Set;

import com.notification.sms.utils.Converter;

@Component
public class RedisBlacklistDaoImpl implements RedisBlacklistDao{
    private JedisPool jedisPool;

    private String blacklist="blacklist";

    @Autowired
    public RedisBlacklistDaoImpl(JedisPool jedisPool){
        this.jedisPool=jedisPool;
    }

    @Override
    public boolean isPresent(PhoneNumber phoneNumber) throws Exception {
        if(phoneNumber==null){
            throw new NullPhoneNumberException();
        }
        try(Jedis jedis=jedisPool.getResource()){
            return jedis.sismember(blacklist,phoneNumber.getPhoneNumber());
        }
    }

    @Override
    public void saveAll(List<PhoneNumber> phoneNumberList) throws Exception {
        if(phoneNumberList==null){
            throw new NullPhoneNumberListException();
        }
        try(Jedis jedis=jedisPool.getResource()){
            Pipeline pipeline=jedis.pipelined();
            for (PhoneNumber phoneNumber : phoneNumberList) {
                if(phoneNumber==null)
                    throw new NullItemPhoneNumberListException();
                pipeline.sadd(blacklist, phoneNumber.getPhoneNumber());
            }
            pipeline.sync();
        }
    }

    @Override
    public void deleteAll(List<PhoneNumber> phoneNumberList) throws Exception {
        if(phoneNumberList==null){
            throw new NullPhoneNumberListException();
        }
        try(Jedis jedis=jedisPool.getResource()){
            Pipeline pipeline=jedis.pipelined();
            for (PhoneNumber phoneNumber : phoneNumberList) {
                if(phoneNumber==null)
                    throw new NullItemPhoneNumberListException();
                pipeline.srem(blacklist, phoneNumber.getPhoneNumber());
            }
            pipeline.sync();
        }
    }

    @Override
    public List<PhoneNumber> getAll() throws Exception {
        Set <String> blacklistedPhoneNumbersSet=null;
        try(Jedis jedis=jedisPool.getResource()){
           blacklistedPhoneNumbersSet = jedis.smembers(blacklist);
        }
        List <PhoneNumber> blacklistedPhoneNumberList=Converter.convertToPhoneNumberType(blacklistedPhoneNumbersSet);
        return blacklistedPhoneNumberList;
    }

    @Override
    protected void finalize() throws Throwable {
        jedisPool.close();
    }
}
