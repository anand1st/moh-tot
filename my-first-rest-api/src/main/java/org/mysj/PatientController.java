package org.mysj;

import org.mysj.dto.Patient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PatientController {

    private final Map<Integer, Patient> map = new HashMap<>();

    @PostMapping("/patient")
    void createPatient(@RequestBody Patient patient) {
        map.put(patient.getPatientNo(), patient);
    }

    @GetMapping("/patient/{id}")
    Patient getPatient(@PathVariable("id") Integer patientId) {
        return map.get(patientId);
    }
}
