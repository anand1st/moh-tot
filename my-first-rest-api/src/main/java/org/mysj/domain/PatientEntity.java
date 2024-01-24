package org.mysj.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "PATIENT", indexes = @Index(unique = true, columnList = "PATIENT_ID"))
public class PatientEntity extends AbstractJpaAuditable<Long> {

    @Column(name = "PATIENT_ID")
    private String patientId;
    @Column(name = "AGE")
    private Integer age;
    @Column(name = "NAME")
    private String name;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
