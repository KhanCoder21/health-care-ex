package com.arsad.service;

import com.arsad.entity.Doctor;

import java.util.List;

public interface DoctorService {

    Long saveDoctor(Doctor doctor);

    List<Doctor> getAllDoctor();

    void removeDoctorById(Long id);

    Doctor getDoctorById(Long id);

    void updateDoctor(Doctor doctor);

    boolean isEmailIdExist(String email);
    boolean isEmailIdExistForEdit(String email, Long id);
}
