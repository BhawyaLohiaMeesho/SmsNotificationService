package com.notification.sms.dao.redis;

import com.notification.sms.entity.PhoneNumber;

import java.util.List;

public interface RedisBlacklistDao {

   public boolean isPresent(PhoneNumber phoneNumber) throws Exception;

   public void saveAll(List<PhoneNumber> phoneNumberList) throws Exception;

   public void deleteAll(List <PhoneNumber> phoneNumberList) throws Exception;

   public List<PhoneNumber> getAll() throws Exception;

}
