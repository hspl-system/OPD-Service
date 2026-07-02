package com.healthify.opdservice.entities;

import java.time.LocalDateTime;

public class Doctor {

   private final String uuid;
   private final String hospitalUuid;
   private final String specialization;
   private final LocalDateTime joiningTime;
   private final String name;

   private Doctor(Builder builder){
       this.uuid=builder.uuid;
       this.hospitalUuid=builder.uuid;
       this.specialization=builder.specialization;
       this.joiningTime=builder.joiningTime;
       this.name=builder.name;

   }

    public String getSpecialization() {
        return specialization;
    }

    public LocalDateTime getJoiningTime() {
        return joiningTime;
    }
    public String getUuid() {
        return uuid;
    }

    public String getHospitalUuid() {
        return hospitalUuid;
    }

    public String getName() {
        return name;
    }

    public static class Builder{
        private String uuid;
        private String hospitalUuid;
        private String specialization;
        private LocalDateTime joiningTime;
        private String name;

        public Builder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setHospitalUuid(String hospitalUuid) {
            this.hospitalUuid = hospitalUuid;
            return this;
        }

        public Builder setSpecialization(String specialization) {
            this.specialization = specialization;
            return this;
        }

        public Builder setJoiningTime(LocalDateTime joiningTime) {
            this.joiningTime = joiningTime;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Doctor build(){
            return new Doctor(this);
        }
    }

}
