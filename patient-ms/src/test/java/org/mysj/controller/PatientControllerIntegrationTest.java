package org.mysj.controller;

import org.junit.jupiter.api.*;
import org.mysj.Main;
import org.mysj.commons.utils.JsonUtils;
import org.mysj.domain.PatientRepo;
import org.mysj.dto.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Main.class, properties = {
        "spring.datasource.url=jdbc:h2:mem:db0",
        "spring.datasource.username=sa",
        "spring.datasource.password="
})
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PatientControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private PatientRepo patientRepo;

    @Test
    @Order(1)
    void testCreatePatient() throws Exception {
        var patient = new PatientDto("010101-01-0101", "Bob", 10,
                "+0123456789", "bob@cat.com");
        mvc.perform(post("/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objToString(patient)))
                .andExpect(result -> {
                    assertEquals(200, result.getResponse().getStatus());
                    assertEquals("1", result.getResponse().getContentAsString());
                    assertEquals("bob@cat.com", patientRepo.findById(1L).orElseThrow().getEmail());
                });
    }

    @Test
    @Order(2)
    void testGetPatient() throws Exception {
        var patient = new PatientDto("010101-01-0101", "Bob", 10,
                "+0123456789", "bob@cat.com");
        mvc.perform(get("/patient/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(result -> {
                    assertEquals(200, result.getResponse().getStatus());
                    assertEquals(patient, JsonUtils.stringToObj(result.getResponse().getContentAsString(),
                            PatientDto.class));
                });
    }

    @Test
    @Order(3)
    void testUpdatePatient() throws Exception {
        var modifiedPatient = new PatientDto();
        modifiedPatient.setEmail("fat@cat.com");
        mvc.perform(post("/patient/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.objToString(modifiedPatient)))
                .andExpect(result -> {
                    assertEquals(200, result.getResponse().getStatus());
                    assertEquals("", result.getResponse().getContentAsString());
                    var p = patientRepo.findById(1L).orElseThrow();
                    assertEquals("fat@cat.com", p.getEmail());
                    assertEquals("010101-01-0101", p.getPatientId());
                    assertEquals("Bob", p.getName());
                    assertEquals(10, p.getAge());
                    assertEquals("+0123456789", p.getContactNo());
                });
    }

    @Test
    @Order(4)
    void testDeletePatient() throws Exception {
        mvc.perform(delete("/patient/1"))
                .andExpect(result -> {
                    assertEquals(200, result.getResponse().getStatus());
                    assertTrue(patientRepo.findAll().isEmpty());
                });
    }
}
