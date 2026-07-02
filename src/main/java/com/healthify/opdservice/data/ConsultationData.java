package com.healthify.opdservice.data;

import com.healthify.opdservice.entities.Consultation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConsultationData {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ConsultationData.class);

    ConsultationData(@Qualifier("opdJdbcTemplate") JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public String addConsultation(Consultation consultation) {

        String query = """
            INSERT INTO consultation
            (consultation_id,
             patient_id,
             doctor_id,
             parent_consultation_id,
             is_first_consult,
             symptoms,
             notes,
             consultation_time)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

        jdbcTemplate.update(
                query,
                consultation.getUuid(),
                consultation.getPatient().getId(),
                consultation.getDoctor().getUuid(),
                consultation.getParentConsultationId(),
                consultation.isFirstConsultation(),
                consultation.getSymptoms(),
                consultation.getNotes(),
                consultation.getTime()
        );

        logger.info("saved consultation to db with uuid: {}",consultation.getUuid());
        return consultation.getUuid();
    }
}
