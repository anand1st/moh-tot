package org.mysj.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PatientDto {

    @NotEmpty
    private String patientId;
    @NotEmpty
    private String name;
    private int age;
    @NotEmpty
    private String contactNo;
    @Email
    @NotNull
    private String email;
}
