package com.notification.sms.constant;

public class Data {
    public static final String KAFKA_SERVER_ADDRESS="localhost:9092";
    public static final String TOPIC_SMS_REQUEST="notification.sms_request";

    public static final String ELASTICSEARCH_SERVER_ADDRESS="localhost:9200";
    public static final String IMICONNECT_URL="https://api.imiconnect.io/resources/v1/messaging";
    public static final String IMICONNECT_API_KEY="93ceffda-5941-11ea-9da9-025282c394f2";
    public static final String IMICONNECT_SUCCESS_CODE="1001";

    public static final String SMS_REQUEST_STATUS_SENT="SENT";

    public static final String SMS_REQUEST_STATUS_QUEUED="QUEUED";

    public static final String SMS_REQUEST_STATUS_FAILURE="FAILURE";

    public static final String SMS_REQUEST_CODE_INTERNAL_SERVER_ERROR="7006";

    public static final String SMS_REQUEST_CODE_PHONE_NUMBER_BLACKLISTED="7001";
}
