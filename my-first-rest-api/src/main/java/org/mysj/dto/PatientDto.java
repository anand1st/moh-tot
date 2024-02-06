package org.mysj.dto;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PatientDto {

    private String patientId;
    private String name;
    private int age;
    private String contactNo;
}
