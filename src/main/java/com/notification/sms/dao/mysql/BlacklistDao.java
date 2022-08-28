package com.notification.sms.dao.mysql;

import com.notification.sms.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlacklistDao extends JpaRepository<PhoneNumber,String> {
}
