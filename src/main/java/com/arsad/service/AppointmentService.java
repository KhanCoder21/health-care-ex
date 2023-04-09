package com.arsad.service;

import com.arsad.entity.Appointment;

import java.util.List;

/* Created by Arsad on 2023-03-24 22:06 */
public interface AppointmentService {

    Long saveAppointment(Appointment appointment);

    List<Appointment> getAllAppointment();

    void removeAppointmentById(Long id);

    Appointment getAppointmentById(Long id);

    void updateAppointment(Appointment appointment);

    boolean isEmailIdExist(String email);

    boolean isEmailIdExistForEdit(String email, Long id);

    List<Object[]> fetchAppointmentsByDoctor(Long docId);

    List<Object[]> fetchAppointmentsByDoctorEmail(String username);

    void updateSlotCountForAppointment(Long id, int count);
}
