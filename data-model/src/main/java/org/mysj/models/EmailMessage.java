package org.mysj.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Data
public class EmailMessage {

    @Email
    @NotNull
    private String recipient;
    @NotEmpty
    private String subject;
    @NotEmpty
    private String content;
}
