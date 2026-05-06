-- =========================================
-- OPD SERVICE DATABASE SCHEMA
-- =========================================


-- =========================================
-- HOSPITAL
-- =========================================
CREATE TABLE hospital (
    hospital_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);


-- =========================================
-- DOCTOR
-- =========================================
CREATE TABLE doctor (
    doctor_id VARCHAR(50) PRIMARY KEY,
    hospital_id VARCHAR(50) NOT NULL,
    name VARCHAR(255),
    specialization VARCHAR(255),
    date_of_joining DATE,

    CONSTRAINT fk_doctor_hospital
        FOREIGN KEY (hospital_id)
        REFERENCES hospital(hospital_id)
);


-- =========================================
-- TECHNICIAN
-- =========================================
CREATE TABLE technician (
    technician_id VARCHAR(50) PRIMARY KEY,
    hospital_id VARCHAR(50) NOT NULL,
    name VARCHAR(255),
    specialization VARCHAR(255),
    date_of_joining DATE,

    CONSTRAINT fk_technician_hospital
        FOREIGN KEY (hospital_id)
        REFERENCES hospital(hospital_id)
);


-- =========================================
-- PATIENT
-- =========================================
CREATE TABLE patient (
    patient_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    phone BIGINT
);


-- =========================================
-- PATIENT DETAILS
-- =========================================
CREATE TABLE patient_details (
    patient_id VARCHAR(50) PRIMARY KEY,
    gender VARCHAR(20),
    blood_group VARCHAR(10),
    marital_status VARCHAR(20),

    CONSTRAINT fk_patientdetails_patient
        FOREIGN KEY (patient_id)
        REFERENCES patient(patient_id)
);


-- =========================================
-- EMERGENCY CONTACT
-- =========================================
CREATE TABLE emergency_contact (
    contact_id VARCHAR(50) PRIMARY KEY,
    patient_id VARCHAR(50) NOT NULL,
    name VARCHAR(255),
    relationship VARCHAR(50),
    contact_number BIGINT,

    CONSTRAINT fk_contact_patient
        FOREIGN KEY (patient_id)
        REFERENCES patient(patient_id)
);


-- =========================================
-- CONSULTATION
-- =========================================
CREATE TABLE consultation (
    consultation_id VARCHAR(50) PRIMARY KEY,
    patient_id VARCHAR(50) NOT NULL,
    doctor_id VARCHAR(50) NOT NULL,

    parent_consultation_id VARCHAR(50),

    is_first_consult BOOLEAN,
    symptoms TEXT,
    notes TEXT,
    consultation_time TIMESTAMP,

    CONSTRAINT fk_consult_patient
        FOREIGN KEY (patient_id)
        REFERENCES patient(patient_id),

    CONSTRAINT fk_consult_doctor
        FOREIGN KEY (doctor_id)
        REFERENCES doctor(doctor_id),

    CONSTRAINT fk_parent_consultation
        FOREIGN KEY (parent_consultation_id)
        REFERENCES consultation(consultation_id)
);


-- =========================================
-- PRESCRIPTION
-- =========================================
CREATE TABLE prescription (
    prescription_id VARCHAR(50) PRIMARY KEY,
    consultation_id VARCHAR(50) UNIQUE,

    CONSTRAINT fk_prescription_consult
        FOREIGN KEY (consultation_id)
        REFERENCES consultation(consultation_id)
);


-- =========================================
-- MEDICINE
-- =========================================
CREATE TABLE medicine (
    medicine_id VARCHAR(50) PRIMARY KEY,
    prescription_id VARCHAR(50) NOT NULL,

    name VARCHAR(255),
    dosage VARCHAR(50),
    frequency VARCHAR(50),
    days INT,

    CONSTRAINT fk_medicine_prescription
        FOREIGN KEY (prescription_id)
        REFERENCES prescription(prescription_id)
);


-- =========================================
-- TEST
-- =========================================
CREATE TABLE test (
    test_id VARCHAR(50) PRIMARY KEY,
    consultation_id VARCHAR(50) NOT NULL,

    test_name VARCHAR(255),
    sample_type VARCHAR(50),
    test_time TIMESTAMP,

    CONSTRAINT fk_test_consult
        FOREIGN KEY (consultation_id)
        REFERENCES consultation(consultation_id)
);


-- =========================================
-- REPORT
-- =========================================
CREATE TABLE report (
    report_id VARCHAR(50) PRIMARY KEY,
    test_id VARCHAR(50) UNIQUE,
    technician_id VARCHAR(50),

    status VARCHAR(50),
    report_file TEXT,
    report_time TIMESTAMP,

    CONSTRAINT fk_report_test
        FOREIGN KEY (test_id)
        REFERENCES test(test_id),

    CONSTRAINT fk_report_technician
        FOREIGN KEY (technician_id)
        REFERENCES technician(technician_id)
);


-- =========================================
-- PAYMENT
-- =========================================
CREATE TABLE payment (
    payment_id VARCHAR(50) PRIMARY KEY,
    consultation_id VARCHAR(50) NOT NULL,

    mode VARCHAR(50),
    payee VARCHAR(255),
    reason VARCHAR(255),
    status VARCHAR(50),
    amount DECIMAL(10,2),
    payment_time TIMESTAMP,

    CONSTRAINT fk_payment_consult
        FOREIGN KEY (consultation_id)
        REFERENCES consultation(consultation_id)
);


-- =========================================
-- CONSULTATION ROOM
-- =========================================
CREATE TABLE consultation_room (
    room_id VARCHAR(50) PRIMARY KEY,
    consultation_id VARCHAR(50) NOT NULL,
    room_number VARCHAR(50),

    CONSTRAINT fk_room_consult
        FOREIGN KEY (consultation_id)
        REFERENCES consultation(consultation_id)
);