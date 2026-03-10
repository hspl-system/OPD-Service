package com.healthify.opdservice.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Prescription {

    private final List<String> medicines;
    private final List<Payments> payments;
    private final Map<String,String> dosage;
    private final List<Test> postChecks;
    private final LocalDateTime dateTime;


    private Prescription(Builder builder) {
        this.medicines = builder.medicines;
        this.payments = builder.payments;
        this.dosage = builder.dosage;
        this.postChecks = builder.postChecks;
        this.dateTime = builder.dateTime;
    }


    public List<String> getMedicines() {
        return medicines;
    }

    public List<Payments> getPayments() {
        return payments;
    }

    public Map<String, String> getDosage() {
        return dosage;
    }

    public List<Test> getPostChecks() {
        return postChecks;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public class Builder{

        private  List<String> medicines;
        private  List<Payments> payments;
        private  Map<String,String> dosage;
        private  List<Test> postChecks;
        private  LocalDateTime dateTime;

        public Builder(){}

        private Builder setMedicines(List<String> medicines) {
            this.medicines = medicines;
            return this;
        }

        private Builder setPayments(List<Payments> payments) {
            this.payments = payments;
            return this;
        }

        private Builder setDosage(Map<String, String> dosage) {
            this.dosage = dosage;
            return this;
        }

        private Builder setPostChecks(List<Test> postChecks) {
            this.postChecks = postChecks;
            return this;
        }

        private Builder setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Prescription build(){
            return new Prescription(this);
        }
    }
}