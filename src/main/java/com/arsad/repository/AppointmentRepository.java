package com.arsad.repository;

import com.arsad.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/* Created by Arsad on 2023-03-24 22:04 */
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT aptm.date, aptm.noOfSlots, aptm.amount, aptm.id FROM Appointment aptm  INNER JOIN aptm.doctor as doctor WHERE doctor.id=:docId")
    public List<Object[]> getAppointmentsByDoctor(Long docId);

    @Query("SELECT aptm.date, aptm.noOfSlots, aptm.amount, aptm.details FROM Appointment aptm  INNER JOIN aptm.doctor as doctor WHERE doctor.email=:username")
    public List<Object[]> getAppointmentsByDoctorEmail(String username);

}
