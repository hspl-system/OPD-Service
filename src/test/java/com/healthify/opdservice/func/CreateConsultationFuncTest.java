package com.healthify.opdservice.func;


import com.healthify.opdservice.DTO.request.CreateConsultationRequest;
import com.healthify.opdservice.api.exceptions.OPDServiceException;
import com.healthify.opdservice.data.ConsultationData;
import com.healthify.opdservice.data.DoctorData;
import com.healthify.opdservice.data.PatientData;
import com.healthify.opdservice.entities.Consultation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class CreateConsultationFuncTest {

    private static CreateConsultationRequest request;

    @Mock
    private DoctorData doctorData;
    @Mock
    private PatientData patientData;
    @Mock
    private ConsultationData consultationData;

    @InjectMocks
    private CreateConsultationFunc createConsultationFunc;

    @BeforeAll
    public static void setup(){
        List<String> payIDs = new ArrayList<>();
        payIDs.add("123");

        request = new CreateConsultationRequest();
        request.setDoctorName("TestDoc");
        request.setFirstConsultation(true);
        request.setParentConsultationId("test1d123");
        request.setSymptoms("caugh,cold");
        request.setPaymentId(payIDs);
        request.setFirstConsultation(true);
    }

    @Test
    public void test_createConsultation_success(){

        when(doctorData.getDoctorDataByName(request.getDoctorName())).thenReturn("Doc123");
        when(patientData.getPatientDataByName(request.getPatientName())).thenReturn("patient123");
        when(consultationData.addConsultation(any(Consultation.class))).thenReturn("uuid123");

        String uuid = createConsultationFunc.createConsultation(request);

        assertEquals(uuid,"uuid123");

    }

    @Test()
    public void test_createConsultation_failure(){

        when(doctorData.getDoctorDataByName(request.getDoctorName())).thenReturn(null);

        OPDServiceException exception = assertThrows(OPDServiceException.class,()->createConsultationFunc.createConsultation(request) );
        assertEquals("DOCTOR_NOT_FOUND",exception.getErrorCode());

    }






}
