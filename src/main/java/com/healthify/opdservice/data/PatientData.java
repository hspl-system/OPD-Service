package com.healthify.opdservice.data;

import com.healthify.opdservice.entities.Patient;
import com.healthify.opdservice.util.UuidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PatientData {
    private static Logger logger = LoggerFactory.getLogger(PatientData.class);
    private final JdbcTemplate jdbcTemplate;

    PatientData(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public String addPatientData(Patient data){
        //todo: impl this later, for now use hard coded data
        return null;
    }

    public List<PatientData> getAllPatientData(String id){
        //todo: impl this later, for now use hard coded data
        return null;
    }

    public Patient getPatientDataByName(String name){
        //todo: impl this later, for now use hard coded data
        logger.info("searching patient details for: {}",name);
        String query = "select patient_id from patient where name = ? ";
        List<Patient> result = jdbcTemplate.query( query,
                (rs, rowNum) -> {
            return  new Patient.Builder().setId(rs.getString("patient_id")).build();
                }
                , name
        );
        logger.info("patient if for {} is : {}",name,result.stream().findFirst().get().getId());
        return result.stream().findFirst().get();
    }


}
