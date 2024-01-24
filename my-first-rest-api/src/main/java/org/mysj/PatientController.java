package org.mysj;

import org.mysj.domain.PatientEntity;
import org.mysj.domain.PatientRepo;
import org.mysj.dto.PatientDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {

    private final PatientRepo patientRepo;

    public PatientController(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    @PostMapping("/patient")
    public Long createPatient(@RequestBody PatientDto patient) {
        var entity = new PatientEntity();
        entity.setPatientId(patient.getPatientId());
        entity.setAge(patient.getAge());
        entity.setName(patient.getName());
        return patientRepo.save(entity).getId();
    }

    @GetMapping("/patient/{id}")
    public PatientDto getPatient(@PathVariable("id") Long id) {
        var entity = patientRepo.findById(id).orElseThrow();
        var patient = new PatientDto();
        patient.setPatientId(entity.getPatientId());
        patient.setName(entity.getName());
        patient.setAge(entity.getAge());
        return patient;
    }

    @DeleteMapping("/patient/{id}")
    public void deletePatient(@PathVariable("id") Long id) {
        patientRepo.deleteById(id);
    }

    @PostMapping("/patient/{id}")
    public void updatePatient(@PathVariable("id") Long id,
                              @RequestBody PatientDto patient) {
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
        patientRepo.save(entity);
    }

    @GetMapping("/patient")
    public PatientDto searchPatientByPatientId(
            @RequestParam("patientId") String patientId) {
        var patient = patientRepo.findByPatientId(patientId).orElseThrow();
        return new PatientDto(patient.getPatientId(), patient.getName(),
                patient.getAge());
    }
}
