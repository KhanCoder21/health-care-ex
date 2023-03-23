package com.arsad.repository;

import com.arsad.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/* Created by Arsad on 2023-03-18 02:32 */
public interface PatientRepository extends JpaRepository<Patient, Long> {
   // @Query("SELECT COUNT(email) FROM Patient WHERE email=:email")
  //  Integer getEmailIdCount(String email);

  //  @Query("SELECT COUNT(email) FROM Patient WHERE email=:email AND id!=:id")
  //  Integer getEmailIdCountForEdit(String email, Long id);
}
