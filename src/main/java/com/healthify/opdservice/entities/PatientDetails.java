package com.healthify.opdservice.entities;

import com.healthify.opdservice.enums.BloodGroup;

public class PatientDetails {
    private final String gender;
    private final String patiententUuid;
    private final BloodGroup bloodGroup;
    private final EmergencyContact emergencyContact;
    private final String marritalStatus;

    private PatientDetails(Builder builder){
        this.gender=builder.gender;
        this.patiententUuid=builder.patiententUuid;
        this.bloodGroup=builder.bloodGroup;
        this.emergencyContact=builder.emergencyContact;
        this.marritalStatus=builder.marritalStatus;
    }

    public String getGender() {
        return gender;
    }

    public String getPatiententUuid() {
        return patiententUuid;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public EmergencyContact getEmergencyContact() {
        return emergencyContact;
    }

    public String getMarritalStatus() {
        return marritalStatus;
    }

    public static class Builder {
        private String gender;
        private String patiententUuid;
        private BloodGroup bloodGroup;
        private EmergencyContact emergencyContact;
        private String marritalStatus;

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder setPatiententUuid(String patiententUuid) {
            this.patiententUuid = patiententUuid;
            return this;
        }

        public Builder setBloodGroup(BloodGroup bloodGroup) {
            this.bloodGroup = bloodGroup;
            return this;
        }

        public Builder setEmergencyContact(EmergencyContact emergencyContact) {
            this.emergencyContact = emergencyContact;
            return this;
        }

        public Builder setMarritalStatus(String marritalStatus) {
            this.marritalStatus = marritalStatus;
            return this;
        }

        public PatientDetails build(){
            return new PatientDetails(this);
        }
    }


}
