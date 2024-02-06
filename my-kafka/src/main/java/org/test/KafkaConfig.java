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
    static final String GRP_SMS = "sms";
    static final String GRP_SMS2 = "sms2";

    @KafkaListener(topics = SMS, groupId = GRP_SMS)
    void sendSmsMessage1(SmsMessage sms) {
        log.info("sendSmsMessage1: {}", sms);
    }

//    @KafkaListener(topics = SMS, groupId = GRP_SMS)
//    void sendSmsMessage2(SmsMessage sms) {
//        log.info("sendSmsMessage2: {}", sms);
//    }

    @KafkaListener(topics = SMS, groupId = GRP_SMS2)
    void sendSmsMessage3(SmsMessage sms) {
        log.info("sendSmsMessage3: {}", sms);
    }
}
