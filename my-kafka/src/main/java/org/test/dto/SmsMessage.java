package org.test.dto;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class SmsMessage {

    private String phoneNumber;
    private String message;
}
