package com.healthify.opdservice.entities;

import java.time.LocalDateTime;
import java.util.List;

public class Consultation {

    private final String uuid;
    private final boolean firstConsultation;
    private final String parentConsultationId;

    private final Patient patient;
    private final Doctor doctor;

    private final String symptoms;
    private final String notes;

    private final Prescription prescription;

    private final List<Test> tests;
    private final List<Payments> payments;
    private final List<String> rooms;

    private final LocalDateTime time;

    private Consultation(Builder builder) {
        this.uuid = builder.uuid;
        this.firstConsultation = builder.firstConsultation;
        this.parentConsultationId = builder.parentConsultationId;
        this.patient = builder.patient;
        this.doctor = builder.doctor;
        this.symptoms = builder.symptoms;
        this.notes = builder.notes;
        this.prescription = builder.prescription;
        this.tests = builder.tests;
        this.payments = builder.payments;
        this.rooms = builder.rooms;
        this.time = builder.time;
    }

    public String getUuid() {
        return uuid;
    }

    public boolean isFirstConsultation() {
        return firstConsultation;
    }

    public String getParentConsultationId() {
        return parentConsultationId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public String getNotes() {
        return notes;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public List<Test> getTests() {
        return tests;
    }

    public List<Payments> getPayments() {
        return payments;
    }

    public List<String> getRooms() {
        return rooms;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public static class Builder {

        private String uuid;
        private boolean firstConsultation;
        private String parentConsultationId;

        private Patient patient;
        private Doctor doctor;

        private String symptoms;
        private String notes;

        private Prescription prescription;

        private List<Test> tests;
        private List<Payments> payments;
        private List<String> rooms;

        private LocalDateTime time;

        public Builder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setFirstConsultation(boolean firstConsultation) {
            this.firstConsultation = firstConsultation;
            return this;
        }

        public Builder setParentConsultationId(String parentConsultationId) {
            this.parentConsultationId = parentConsultationId;
            return this;
        }

        public Builder setPatient(Patient patient) {
            this.patient = patient;
            return this;
        }

        public Builder setDoctor(Doctor doctor) {
            this.doctor = doctor;
            return this;
        }

        public Builder setSymptoms(String symptoms) {
            this.symptoms = symptoms;
            return this;
        }

        public Builder setNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder setPrescription(Prescription prescription) {
            this.prescription = prescription;
            return this;
        }

        public Builder setTests(List<Test> tests) {
            this.tests = tests;
            return this;
        }

        public Builder setPayments(List<Payments> payments) {
            this.payments = payments;
            return this;
        }

        public Builder setRooms(List<String> rooms) {
            this.rooms = rooms;
            return this;
        }

        public Builder setTime(LocalDateTime time) {
            this.time = time;
            return this;
        }

        public Consultation build() {
            return new Consultation(this);
        }
    }
}