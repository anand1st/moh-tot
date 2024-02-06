package org.mysj.service;

import org.mysj.dto.PatientDto;

public interface PatientService {

    Long createPatient(PatientDto patient);

    PatientDto getPatient(Long id);

    void deletePatient(Long id);

    void updatePatient(Long id, PatientDto patient);

    PatientDto searchPatient(String patientId);
}
