package com.healthify.opdservice.data;

import com.healthify.opdservice.entities.Doctor;
import com.healthify.opdservice.enums.Constants;
import com.healthify.opdservice.util.UuidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Component
public class DoctorData {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(DoctorData.class);

    //constructor injection
    DoctorData(@Qualifier("opdJdbcTemplate") JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public String adddoctorData(Doctor data){
        //todo: impl this later,  for now add into hard coded list

        return null;
    }

    public List<Doctor> getAlldoctorData(String id){
        //todo: impl this later, for now return hard coded list
        return null;
    }

    public Doctor getDoctorDataByName(String name){
        //todo: impl this later,for now return 1 record from hard coded list
        logger.info("getting doctor id for name: {}",name);

        String query = "select doctor_id from doctor where name = ? ";
        List<String> result = jdbcTemplate.query(query
                ,(rs, rowNum) -> rs.getString("doctor_id")
                ,name);

        logger.info("id for doctor :{} is : {}",name,result.stream().findFirst());
        return new Doctor.Builder().setUuid(result.stream().findFirst().get()).build();
    }


}
