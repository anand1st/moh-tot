package org.mysj.controller;

import org.junit.jupiter.api.Test;
import org.mysj.commons.utils.JsonUtils;
import org.mysj.dto.PatientDto;
import org.mysj.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(value = PatientController.class)
class PatientControllerTest {

    @MockBean
    private PatientService patientService;
    @Autowired
    private MockMvc mvc;

    @Test
    void testCreatePatient() throws Exception {
        var obj = new PatientDto("010101-01-0101", "Bob", 10, "+0123456789",
                "bob@cat.com");
        when(patientService.createPatient(obj)).thenReturn(1L);
        mvc.perform(post("/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objToString(obj)))
                .andExpect(result -> {
                    assertEquals(200, result.getResponse().getStatus());
                    assertEquals("1", result.getResponse().getContentAsString());
                    verify(patientService, times(1)).createPatient(obj);
                });
    }
}
