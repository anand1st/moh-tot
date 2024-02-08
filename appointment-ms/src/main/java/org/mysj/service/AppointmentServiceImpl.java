package org.mysj.service;

import org.mysj.dto.AppointmentDto;
import org.mysj.dto.PatientDto;
import org.mysj.models.EmailMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.mysj.models.SmsMessage;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;
import java.util.Map;

import static java.util.Objects.requireNonNull;

@lombok.RequiredArgsConstructor
@lombok.extern.slf4j.Slf4j
@Service
class AppointmentServiceImpl implements AppointmentService {

    private static final String APPOINTMENT_EMAIL_TEMPLATE = """
            <!DOCTYPE html>
            <html xmlns:th="http://www.thymeleaf.org">
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            </head>
            <body>
                <div>Appointment Date: <b th:text="${appointmentDate}"></b></div>
                <div>Appointment Time: <b th:text="${appointmentTime}"></b></div>
                <div>Location: <b th:text="${appointmentLocation}"></b></div>
            </body>
            </html>
            """;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final TemplateEngine thymeleafTemplateEngine;
    private final RestClient patientRestClient = RestClient.builder()
            .baseUrl("http://localhost:8080")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    @Override
    public void createAppointment(AppointmentDto appointment) {
        var patient = patientRestClient.get()
                .uri("/patient",
                        uriBuilder -> uriBuilder.queryParam("patientId", appointment.getPatientId()).build())
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new RuntimeException("Error connecting to patient-ms");
                })
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new IllegalArgumentException("Invalid patient id provided");
                })
                .body(PatientDto.class);
        var message = String.format("Appointment Time: %s, Appointment Date: %s, Location: %s",
                appointment.getTime(), appointment.getDate(), appointment.getLocation());
        var smsMessage = new SmsMessage(requireNonNull(patient).getContactNo(), message);
        kafkaTemplate.send("sms", smsMessage);

        var context = new Context(Locale.ENGLISH, Map.of(
                "appointmentTime", appointment.getTime(),
                "appointmentDate", appointment.getDate(),
                "appointmentLocation", appointment.getLocation()));
        var mailContent = thymeleafTemplateEngine.process(APPOINTMENT_EMAIL_TEMPLATE, context);
        kafkaTemplate.send("email", new EmailMessage(patient.getEmail(), "Appointment", mailContent));
    }
}
