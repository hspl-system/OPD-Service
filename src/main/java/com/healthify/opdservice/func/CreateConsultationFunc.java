package com.healthify.opdservice.func;

import com.healthify.opdservice.DTO.request.CreateConsultationRequest;
import com.healthify.opdservice.data.ConsultationData;
import com.healthify.opdservice.data.DoctorData;
import com.healthify.opdservice.data.PatientData;
import com.healthify.opdservice.entities.Consultation;
import com.healthify.opdservice.api.exceptions.OPDServiceException;
import com.healthify.opdservice.util.UuidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.healthify.opdservice.enums.OPDServiceExceptionErrorCodes.*;

@Component
public class CreateConsultationFunc {
    @Autowired
    private DoctorData doctorData;
    @Autowired
    private PatientData patientData;
    @Autowired
    private ConsultationData consultationData;

    private static Logger logger =  LoggerFactory.getLogger(CreateConsultationFunc.class);

    public String createConsultation(CreateConsultationRequest consultation){
        logger.info("in CreateConsultationFunc.class");

        //create uuid
        String uuid = UuidUtils.getnewUuid();


        //get doc uuid from db
        String docUuid = doctorData.getDoctorDataByName(consultation.getDoctorName());
        if(docUuid == null){
            logger.error("error fetching doctor info for name {}", consultation.getDoctorName());
            throw new OPDServiceException(DOCTOR_NOT_FOUND,consultation.getDoctorName());
        }else {
            logger.info("git docs info {}", docUuid);
        }

        //get patientID from db
        String patientUuid = patientData.getPatientDataByName(consultation.getPatientName());
        if(patientUuid == null){
            logger.error("error fetching patient info for name {}", consultation.getPatientName());
            throw new OPDServiceException(PATIENT_NOT_FOUND,consultation.getPatientName());
        }else {
            logger.info("got patient info {}", patientUuid);
        }

        // create Consultation obj
        Consultation consultationBuilder = new Consultation.Builder().setFirstConsultation(true)
                .setUuid(uuid)
                .setParentConsultationId(null)
                .setMeds(null)
                .setNotes(null)
                .setDoctorId(docUuid)
                .setPatientId(patientUuid)
                .setRooms(null)
                .setPaymentId(consultation.getPaymentId())
                .setSymptoms(consultation.getSymptoms())
                .setTime(LocalDateTime.now())
                .setTestsIds(null)
                .setMeds(null)
                .setParentConsultationId(null)
                .build();
        logger.info("created consultation for uuid: {}",consultationBuilder.getUuid());


        //call db to add consultation
            String consultationUuid = consultationData.addConsultation(consultationBuilder);
            if(consultationUuid == null){
                logger.error("error adding consultation to db for uuid: {}",consultationBuilder.getUuid());
                throw new OPDServiceException(CONSULTATION_CREATION_FAILED);
            }else {
                logger.info("added consultation to db consultationUuid: {}", consultationUuid);
            }

        return consultationUuid;
    }

}
