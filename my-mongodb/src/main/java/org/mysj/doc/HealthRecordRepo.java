package org.mysj.doc;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HealthRecordRepo extends MongoRepository<HealthRecordDoc, String> {

    Optional<HealthRecordDoc> findByPatientId(String patientId);
}
