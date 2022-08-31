package com.notification.sms.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class SmsWithinTimeRangeRequest {
    @JsonProperty("start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;
    @JsonProperty("end_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;

    @JsonProperty("page_number")
    private Integer pageNumber;

    @JsonProperty("page_size")
    private Integer pageSize;

    public SmsWithinTimeRangeRequest(){
        pageNumber=0;
        pageSize=5;
    }

    public SmsWithinTimeRangeRequest(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public SmsWithinTimeRangeRequest(LocalDateTime startTime, LocalDateTime endTime, Integer pageNumber, Integer pageSize) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void checkRequiredValues() throws Exception{
         if(startTime==null)
             throw new Exception("start time is null");
         if(endTime==null)
             throw new Exception("end time is null");
    }

    @Override
    public String toString() {
        return "SmsWithinTimeRangeRequest{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }
}
