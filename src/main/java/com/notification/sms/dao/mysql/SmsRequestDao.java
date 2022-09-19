package com.notification.sms.dao.mysql;

import com.notification.sms.entity.SmsRequest;
import com.notification.sms.response.SmsRequestStatus;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SmsRequestDao extends JpaRepository <SmsRequest,Integer> {
//    @Modifying
//    @Query("update sms_request u set u.status = :status , u.failure_code = :code , u.failure_comments= :comments where u.id = :id")
//    public void updateSmsRequest(@Param(value="id") Integer requestId,@Param(value = "status") String status,
//                                       @Param(value = "code") String code,@Param(value = "comments") String comments);
     public SmsRequest updateSmsRequestStatus(SmsRequest smsRequest, SmsRequestStatus smsRequestStatus);
}
