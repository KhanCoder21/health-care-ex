package com.arsad.service;

import com.arsad.entity.Patient;

import java.util.List;

public interface PatientService {

    Long savePatient(Patient patient);

    List<Patient> getAllPatient();

    Patient getPatientByEmail(String email);

    void removePatientById(Long id);

    Patient getPatientById(Long id);

    void updatePatient(Patient patient);

    boolean isEmailIdExist(String email);

    boolean isEmailIdExistForEdit(String email, Long id);
}
