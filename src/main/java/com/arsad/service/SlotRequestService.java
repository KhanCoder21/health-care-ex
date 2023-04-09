package com.arsad.service;

import com.arsad.entity.SlotRequest;

import java.util.List;

/* Created by Arsad on 2023-04-08 22:56 */
public interface SlotRequestService {

    /*pateint can see his slot*/
    List<SlotRequest> getSlotByPatientEmail(String patientName);
    List<SlotRequest> getSlotByDoctorEmail(String doctorName);

    /*Patient can book slot*/
    Long saveSlotRequest(SlotRequest slotRequest);

    /*Admin/patient can view all slots*/
    List<SlotRequest> getAllSlotRequests();

    /*Patient can update the req status*/
    void updateSlotRequestStatus(Long id, String status);
}
