package org.mysj.models;

import jakarta.validation.constraints.NotEmpty;

@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Data
public class SmsMessage {

    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private String message;
}
