package org.mysj.domain;

import jakarta.persistence.*;
import org.mysj.commons.domain.AbstractJpaAuditable;

@lombok.EqualsAndHashCode(callSuper = true)
@lombok.Data
@Entity
@Table(name = "PATIENT", indexes = @Index(unique = true, columnList = "PATIENT_ID"))
public class PatientEntity extends AbstractJpaAuditable<Long> {

    @Column(name = "PATIENT_ID")
    private String patientId;
    @Column(name = "AGE")
    private Integer age;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CONTACT_NO")
    private String contactNo;
}
