package com.notification.sms.dao.elasticsearch;

import com.notification.sms.entity.SmsRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ElasticsearchSmsRequestDao extends ElasticsearchRepository<SmsRequest,Integer> {
   public Page<SmsRequest> findByUpdatedAtBetween(LocalDateTime startTime, LocalDateTime endTime, Pageable pageable);
   public List <SmsRequest> findByMessage(String text, Pageable pageable);
}
