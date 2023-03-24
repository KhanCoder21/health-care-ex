package com.arsad.repository;

import com.arsad.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

/* Created by Arsad on 2023-03-24 22:04 */
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
