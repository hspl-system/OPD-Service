package com.healthify.opdservice.DTO.request;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateConsultationRequest {
    @NotNull(message = "cant be null")
    private boolean isFirstConsultation;
    @NotNull(message = "cant be null")
    private String parentConsultationId;

    private String symptoms;
    @Size(min=1, message = "at least one payment id required")
    private List<String> paymentId;
    private LocalDateTime time;
    @NotNull(message = "cant be null")
    private String doctorName;
    @NotNull(message = "cant be null")
    private String patientName;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public boolean isFirstConsultation() {
        return isFirstConsultation;
    }

    public void setFirstConsultation(boolean firstConsultation) {
        isFirstConsultation = firstConsultation;
    }

    public String getParentConsultationId() {
        return parentConsultationId;
    }

    public void setParentConsultationId(String parentConsultationId) {
        this.parentConsultationId = parentConsultationId;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public List<String> getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(List<String> paymentId) {
        this.paymentId = paymentId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
