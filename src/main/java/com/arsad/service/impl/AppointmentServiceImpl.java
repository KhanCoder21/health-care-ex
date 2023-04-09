package com.arsad.service.impl;

import com.arsad.entity.Appointment;
import com.arsad.exception.AppointmentNotFoundException;
import com.arsad.repository.AppointmentRepository;
import com.arsad.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/* Created by Arsad on 2023-03-24 22:09 */
@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository repository;

    @Override
    public Long saveAppointment(Appointment appointment) {
        return repository.save(appointment).getId();
    }

    @Override
    public List<Appointment> getAllAppointment() {
        return repository.findAll();
    }

    @Override
    public void removeAppointmentById(Long id) {
        repository.delete(getAppointmentById(id));
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return repository.findById(id).orElseThrow(() -> new AppointmentNotFoundException(id + ", not exists"));
    }

    /**
     * @param appointment appointment
     */
    @Override
    public void updateAppointment(Appointment appointment) {
        if (repository.existsById(appointment.getId())) {
            repository.save(appointment);
        } else {
            throw new AppointmentNotFoundException(appointment.getId() + ", not exists");
        }
    }

    /**
     * @param email email
     * @ true or false
     */
    @Override
    public boolean isEmailIdExist(String email) {
        return false;
    }

    /**
     * @param email email
     * @param id    id
     * @return true or false
     */
    @Override
    public boolean isEmailIdExistForEdit(String email, Long id) {
        return false;
    }

    /**
     * @param docId
     * @return
     */
    @Override
    public List<Object[]> fetchAppointmentsByDoctor(Long docId) {
        return repository.getAppointmentsByDoctor(docId);
    }

    @Override
    public List<Object[]> fetchAppointmentsByDoctorEmail(String username) {
        return repository.getAppointmentsByDoctorEmail(username);
    }

    @Override
    @Transactional
    public void updateSlotCountForAppointment(Long id, int count) {
        repository.updateSlotCountForAppointment(id, count);
    }
}
