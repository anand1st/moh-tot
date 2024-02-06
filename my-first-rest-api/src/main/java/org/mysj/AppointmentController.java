package org.mysj;

import org.mysj.dto.AppointmentDto;
import org.mysj.service.AppointmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@lombok.RequiredArgsConstructor
@RestController
class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/appointment")
    void createAppointment(@RequestBody AppointmentDto appointment) {
        appointmentService.createAppointment(appointment);
    }
}
