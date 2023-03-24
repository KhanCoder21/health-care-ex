package com.arsad.service;

import com.arsad.entity.Doctor;

import java.util.List;
import java.util.Map;

public interface DoctorService {

    Long saveDoctor(Doctor doctor);

    List<Doctor> getAllDoctor();

    void removeDoctorById(Long id);

    Doctor getDoctorById(Long id);

    void updateDoctor(Doctor doctor);

    boolean isEmailIdExist(String email);

    boolean isEmailIdExistForEdit(String email, Long id);

    Map<Long, String> getDocIdAndName();
}
