package org.mysj.service;

import org.mysj.dto.PatientDto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public interface PatientService {

    Long createPatient(PatientDto patient);

    @Cacheable(value = "patient")
    PatientDto getPatient(Long id);

    @CacheEvict(value = "patient")
    void deletePatient(Long id);

    @CacheEvict(value = "patient", key = "#id")
    void updatePatient(Long id, PatientDto patient);

    PatientDto searchPatient(String patientId);
}
