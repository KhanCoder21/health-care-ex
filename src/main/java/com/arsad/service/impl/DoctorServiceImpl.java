package com.arsad.service.impl;

import com.arsad.entity.Doctor;
import com.arsad.entity.User;
import com.arsad.enums.UserRole;
import com.arsad.exception.DoctorNotFoundException;
import com.arsad.repository.DoctorRepository;
import com.arsad.service.DoctorService;
import com.arsad.service.UserService;
import com.arsad.utils.CollectionUtils;
import com.arsad.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/* Created by Arsad on 2023-03-18 02:38 */
@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserUtils userUtils;

    @Override
    public Long saveDoctor(Doctor doctor) {
        Long id = repository.save(doctor).getId();
        if (null != id) {
            User user = new User();
            user.setDisplayName(doctor.getFirstName() + " " + doctor.getLastName());
            user.setUserName(doctor.getEmail());
            user.setPassword(userUtils.generatePassword());
            user.setUserRole(UserRole.DOCTOR.name());
            userService.saveUser(user);
            /* TODO : Email part is pending */
        }
        return id;
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

    @Override
    public Map<Long, String> getDocIdAndName() {
        List<Object[]> docIdAndNameList = repository.getDocIdAndName();
        Map<Long, String> docIdAndNameMap = CollectionUtils.convertListToMapThirdIndex(docIdAndNameList);
        return docIdAndNameMap;
    }
}
