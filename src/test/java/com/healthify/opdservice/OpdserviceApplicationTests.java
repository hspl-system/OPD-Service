package com.healthify.opdservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


    @SpringBootTest
    @AutoConfigureMockMvc
    public  class OpdserviceApplicationTests {

        @Autowired
        MockMvc mockMvc;

        @Test
        public void test_creatConsultation_success() throws Exception {
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

            mockMvc.perform(post("/consultation/create").contentType("application/json").content(request)).andExpect(status().isOk());
        }

        @Test
        public void test_createConsultation_invalid_docName() throws Exception {
            String request = """
                    {
                        "firstConsultation": true,
                        "parentConsultationId": "CONS-12345",
                        "symptoms": "Fever , cough, since two days",
                        "paymentId": ["PAY-101", "PAY-102"],
                        "time": "2026-02-05T10:30:00",
                        "doctorName": "xyz",
                        "patientName": "Neha Singh"
                      }
                    """;

            mockMvc.perform(post("/consultation/create").content(request).contentType("application/json")).andExpect(status().is5xxServerError()).andExpect(content().string("{\"errorCode\":\"DOCTOR_NOT_FOUND\",\"errorMessage\":\"no such doctor found with name xyz\",\"statusCode\":500,\"timeStamp\":null}"));
        }



}
