package com.notification.sms.request;

public class SmsWithTextRequest {
    private String text;

    private Integer pageNumber;

    private Integer pageSize;

    public SmsWithTextRequest(){
        pageNumber=0;
        pageSize=5;
    }

    public SmsWithTextRequest(String text, Integer pageNumber, Integer pageSize) {
        this.text = text;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void checkRequiredValues() throws Exception{
         if(text==null){
             throw new Exception("text is null");
         }
    }

    @Override
    public String toString() {
        return "SmsWithTextRequest{" +
                "text='" + text + '\'' +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }
}
