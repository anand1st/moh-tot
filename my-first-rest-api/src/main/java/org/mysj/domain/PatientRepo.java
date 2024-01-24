package org.mysj.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepo extends JpaRepository<PatientEntity, Long> {

    Optional<PatientEntity> findByPatientId(String patientId);
}
