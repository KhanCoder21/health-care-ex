package com.arsad.repository;

import com.arsad.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/* Created by Arsad on 2023-03-18 02:32 */
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("SELECT COUNT(email) FROM Doctor WHERE email=:email")
    Integer getEmailIdCount(String email);

    @Query("SELECT COUNT(email) FROM Doctor WHERE email=:email AND id!=:id")
    Integer getEmailIdCountForEdit(String email, Long id);

    @Query("SELECT id, firstName, lastName FROM Doctor")
    List<Object[]> getDocIdAndName();
}
