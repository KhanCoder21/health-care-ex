package com.arsad.service.impl;

import com.arsad.entity.Patient;
import com.arsad.entity.User;
import com.arsad.enums.UserRole;
import com.arsad.exception.PatientNotFoundException;
import com.arsad.repository.PatientRepository;
import com.arsad.service.PatientService;
import com.arsad.service.UserService;
import com.arsad.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* Created by Arsad on 2023-03-18 02:38 */
@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserUtils userUtils;

    @Override
    public Long savePatient(Patient patient) {
        Long id = repository.save(patient).getId();
        if (null != id) {
            User user = new User();
            user.setDisplayName(patient.getFirstName() + " " + patient.getLastName());
            user.setUserName(patient.getEmail());
            user.setPassword(userUtils.generatePassword());
            user.setUserRole(UserRole.PATIENT.name());
            userService.saveUser(user);
            /* TODO : Email part is pending */
        }
        return id;
    }

    @Override
    public List<Patient> getAllPatient() {
        return repository.findAll();
    }

    @Override
    public void removePatientById(Long id) {
        repository.delete(getPatientById(id));
    }

    @Override
    public Patient getPatientById(Long id) {
        return repository.findById(id).orElseThrow(() -> new PatientNotFoundException(id + ", not exists"));
    }

    @Override
    public void updatePatient(Patient patient) {
        if (repository.existsById(patient.getId())) {
            repository.save(patient);
        } else {
            throw new PatientNotFoundException(patient.getId() + ", not exists");
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
