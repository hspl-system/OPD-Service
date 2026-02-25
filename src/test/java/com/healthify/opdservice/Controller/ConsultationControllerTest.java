package com.healthify.opdservice.Controller;

import com.healthify.opdservice.DTO.request.CreateConsultationRequest;
import com.healthify.opdservice.api.exceptions.OPDServiceException;
import com.healthify.opdservice.controller.ConsultationController;
import org.junit.jupiter.api.*;
import com.healthify.opdservice.func.CreateConsultationFunc;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultationControllerTest {


    @Mock
    private CreateConsultationFunc createConsultationFunc;

    @InjectMocks
    private ConsultationController consultationController;

    private CreateConsultationRequest request;

    @BeforeEach
    public void setup(){
        List<String> payIDs = new ArrayList<>();
        payIDs.add("123");

        request = new CreateConsultationRequest();
        request.setDoctorName("TestDoc");
        request.setFirstConsultation(true);
        request.setParentConsultationId("test1d123");
        request.setSymptoms("caugh,cold");
        request.setPaymentId(payIDs);

    }

    @Test
    public void test_ConsultaionController_success(){
        when(createConsultationFunc.createConsultation(request)).thenReturn("Success");

        ResponseEntity<String> result = consultationController.createConsultation(request);

        assertEquals(result.getBody(),"Success");
        assertEquals(result.getStatusCode().value(),200);

    }

    @Test()
    public void Test_failure(){


            when(createConsultationFunc.createConsultation(request)).thenThrow(OPDServiceException.class);

        assertThrows(OPDServiceException.class, () ->
                consultationController.createConsultation(request));


    }


}
