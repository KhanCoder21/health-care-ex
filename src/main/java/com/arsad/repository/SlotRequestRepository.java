package com.arsad.repository;

import com.arsad.entity.SlotRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/* Created by Arsad on 2023-04-08 22:54 */
public interface SlotRequestRepository extends JpaRepository<SlotRequest, Long> {

    @Modifying
    @Query("UPDATE SlotRequest SET status=:status WHERE id=:id")
    void updateSlotRequestStatus(Long id, String status);

    @Query("SELECT slotRequest FROM SlotRequest slotRequest INNER JOIN slotRequest.patient as patient WHERE patient.email=:patientName")
    List<SlotRequest> getSlotByPatientEmail(String patientName);

    @Query("SELECT slotRequest FROM SlotRequest slotRequest INNER JOIN slotRequest.appointment.doctor as doctor WHERE doctor.email=:doctorName")
    List<SlotRequest> getSlotByDoctorEmail(String doctorName);
}
