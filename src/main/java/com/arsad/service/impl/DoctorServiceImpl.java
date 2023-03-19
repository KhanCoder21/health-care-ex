package com.arsad.service.impl;

import com.arsad.entity.Doctor;
import com.arsad.exception.DoctorNotFoundException;
import com.arsad.repository.DoctorRepository;
import com.arsad.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* Created by Arsad on 2023-03-18 02:38 */
@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository repository;

    @Override
    public Long saveDoctor(Doctor doctor) {
        return repository.save(doctor).getId();
    }

    @Override
    public List<Doctor> getAllDoctor() {
        return repository.findAll();
    }

    @Override
    public void removeDoctorById(Long id) {
        repository.delete(getDoctorById(id));
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DoctorNotFoundException(id + ", not exists"));
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        if (repository.existsById(doctor.getId())) {
            repository.save(doctor);
        } else {
            throw new DoctorNotFoundException(doctor.getId() + ", not exists");
        }
    }

    @Override
    public boolean isEmailIdExist(String email) {
        return repository.getEmailIdCount(email) > 0;
    }

    @Override
    public boolean isEmailIdExistForEdit(String email, Long id) {
        return repository.getEmailIdCountForEdit(email, id) > 0;
    }
}
