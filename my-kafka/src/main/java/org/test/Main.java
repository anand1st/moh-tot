package org.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.test.dto.SmsMessage;

import java.util.stream.IntStream;

@lombok.RequiredArgsConstructor
@SpringBootApplication
public class Main implements CommandLineRunner {

    private final KafkaTemplate<String, SmsMessage> kafkaTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        IntStream.range(1, 10).forEach(i -> kafkaTemplate.send(KafkaConfig.SMS, new SmsMessage("+60123456789", "Hello Kawan " + i)));
    }
}