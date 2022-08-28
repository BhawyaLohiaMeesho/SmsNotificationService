package com.notification.sms.dao.mysql;

import com.notification.sms.entity.SmsRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsRequestDao extends JpaRepository <SmsRequest,Integer> {
}
