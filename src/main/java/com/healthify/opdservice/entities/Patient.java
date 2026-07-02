package com.healthify.opdservice.entities;

public class Patient {
    private final String id;
    private final long phone;
    private final String email;
    private final String name;
    private final PatientDetails patientDetails;

    private Patient(Builder builder){
        this.id=builder.id;
        this.phone=builder.phone;
        this.email=builder.email;
        this.name=builder.name;
        this.patientDetails=builder.patientDetails;

    }

    public String getId() {
        return id;
    }

    public long getPhone() {
        return phone;
    }


    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public PatientDetails getPatientDetails(){ return patientDetails; }


    public static class Builder{
        private String id;
        private long phone;
        private String email;
        private String name;
        private  PatientDetails patientDetails;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setPhone(long phone) {
            this.phone = phone;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPatientDetails(PatientDetails details) {
            this.patientDetails = patientDetails;
            return this;
        }

        public Patient build(){
            return new Patient(this);
        }
    }
}
