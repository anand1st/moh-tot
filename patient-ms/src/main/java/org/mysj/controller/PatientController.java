package org.mysj.controller;

import jakarta.validation.Valid;
import org.mysj.dto.PatientDto;
import org.mysj.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
class PatientController {

    private final PatientService patientService;

    PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/patient")
    Long createPatient(@RequestBody @Valid PatientDto patient) {
        return patientService.createPatient(patient);
    }

    @GetMapping("/patient/{id}")
    PatientDto getPatient(@PathVariable("id") Long id) {
        return patientService.getPatient(id);
    }

    @DeleteMapping("/patient/{id}")
    void deletePatient(@PathVariable("id") Long id) {
        patientService.deletePatient(id);
    }

    @PostMapping("/patient/{id}")
    void updatePatient(@PathVariable("id") Long id,
                       @RequestBody PatientDto patient) {
        patientService.updatePatient(id, patient);
    }

    @GetMapping("/patient")
    PatientDto searchPatientByPatientId(
            @RequestParam("patientId") String patientId) {
        return patientService.searchPatient(patientId);
    }
}
