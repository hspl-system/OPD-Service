package com.healthify.opdservice.entities;

public class EmergencyContact {
    private final String name;
    private final String relationship;
    private final long contactNo;

    private EmergencyContact(Builder builder){
        this.name=builder.name;
        this.relationship=builder.relationship;
        this.contactNo=builder.contactNo;
    }

    public String getName() {
        return name;
    }

    public String getRelationship() {
        return relationship;
    }


    public long getContactNo() {
        return contactNo;
    }

    public static class Builder{
        private String name;
        private String relationship;
        private long contactNo;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setRelationship(String relationship) {
            this.relationship = relationship;
            return this;
        }

        public Builder setContactNo(long contactNo) {
            this.contactNo = contactNo;
            return this;
        }

        public EmergencyContact build(){
            return new EmergencyContact(this);
        }
    }
}
