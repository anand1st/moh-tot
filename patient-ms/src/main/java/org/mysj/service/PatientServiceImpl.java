package org.mysj.service;

import org.mysj.domain.PatientEntity;
import org.mysj.domain.PatientRepo;
import org.mysj.dto.PatientDto;
import org.springframework.stereotype.Service;

@lombok.RequiredArgsConstructor
@Service
class PatientServiceImpl implements PatientService {

    private final PatientRepo patientRepo;

    @Override
    public Long createPatient(PatientDto patient) {
        var entity = new PatientEntity();
        entity.setPatientId(patient.getPatientId());
        entity.setAge(patient.getAge());
        entity.setName(patient.getName());
        entity.setContactNo(patient.getContactNo());
        return patientRepo.save(entity).getId();
    }

    @Override
    public PatientDto getPatient(Long id) {
        var entity = patientRepo.findById(id).orElseThrow();
        return new PatientDto(entity.getPatientId(), entity.getName(),
                entity.getAge(), entity.getContactNo());
    }

    @Override
    public void deletePatient(Long id) {
        patientRepo.deleteById(id);
    }

    @Override
    public void updatePatient(Long id, PatientDto patient) {
        var entity = patientRepo.findById(id).orElseThrow();
        if (patient.getAge() > 0) {
            entity.setAge(patient.getAge());
        }
        if (patient.getName() != null) {
            entity.setName(patient.getName());
        }
        if (patient.getPatientId() != null) {
            entity.setPatientId(patient.getPatientId());
        }
        if (patient.getContactNo() != null) {
            entity.setContactNo(patient.getContactNo());
        }
        patientRepo.save(entity);
    }

    @Override
    public PatientDto searchPatient(String patientId) {
        var patient = patientRepo.findByPatientId(patientId).orElseThrow();
        return new PatientDto(patient.getPatientId(), patient.getName(),
                patient.getAge(), patient.getContactNo());
    }
}
