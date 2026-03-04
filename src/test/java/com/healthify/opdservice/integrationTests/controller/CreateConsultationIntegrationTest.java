package com.healthify.opdservice.integrationTests.controller;


import com.healthify.opdservice.controller.ConsultationController;
import com.healthify.opdservice.func.CreateConsultationFunc;
import com.healthify.opdservice.func.CreateConsultationFuncTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConsultationController.class)
public class CreateConsultationIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CreateConsultationFunc createConsultationFunc;

    @Test
    public void testConsultationCreation_success() throws Exception {

        String request = """
                {
                    "firstConsultation": true,
                    "parentConsultationId": "CONS-12345",
                    "symptoms": "Fever , cough, since two days",
                    "paymentId": ["PAY-101", "PAY-102"],
                    "time": "2026-02-05T10:30:00",
                    "doctorName": "Ram",
                    "patientName": "Neha Singh"
                  }
                """;

        when(createConsultationFunc.createConsultation(any())).thenReturn("123");

        mockMvc.perform(post("/consultation/create").contentType("application/json").content(request)).andExpect(status().isOk());
    }


    @Test
    public void testConsultationCreation_validation_failure() throws Exception{
    String request = """
            {
                    "parentConsultationId": "CONS-12345",
                    "symptoms": "Fever",
                    "paymentId": ["PAY-101", "PAY-102"],
                    "time": "2026-02-05T10:30:00",
                    "doctorName": "Ram",
                    "patientName": "Neha Singh"
                  }
            """;

    mockMvc.perform(post("/consultation/create").content(request).contentType("application/json")).andExpect(status().is4xxClientError());

}

}
