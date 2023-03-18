package com.arsad.repository;

import com.arsad.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

/* Created by Arsad on 2023-03-18 02:32 */
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
