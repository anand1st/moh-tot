package org.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.test.dto.SmsMessage;

@lombok.extern.slf4j.Slf4j
@Configuration
@EnableKafka
class KafkaConfig {

    static final String SMS = "sms";
    static final String SEND_SMS_GROUP_ID = "sendSms";
    static final String LOG_SMS_GROUP_ID = "logSms";

    @KafkaListener(topics = SMS, groupId = SEND_SMS_GROUP_ID)
    void sendSmsMessage(SmsMessage sms) {
        log.info("sendSmsMessage: {}", sms);
    }

    @KafkaListener(topics = SMS, groupId = SEND_SMS_GROUP_ID)
    void sendSmsMessage2(SmsMessage sms) {
        log.info("sendSmsMessage2: {}", sms);
    }

    @KafkaListener(topics = SMS, groupId = LOG_SMS_GROUP_ID)
    void logSmsMessage(SmsMessage sms) {
        log.info("logSmsMessage: {}", sms);
    }
}
