package com.arsad.service.impl;

import com.arsad.entity.SlotRequest;
import com.arsad.repository.SlotRequestRepository;
import com.arsad.service.SlotRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/* Created by Arsad on 2023-04-08 23:01 */
@Service
public class SlotRequestServiceImpl implements SlotRequestService {

    @Autowired
    private SlotRequestRepository slotRequestRepo;

    @Override
    public List<SlotRequest> getSlotByPatientEmail(String patientName) {
        return slotRequestRepo.getSlotByPatientEmail(patientName);
    }
    @Override
    public List<SlotRequest> getSlotByDoctorEmail(String doctorName) {
        return slotRequestRepo.getSlotByDoctorEmail(doctorName);
    }

    @Override
    public Long saveSlotRequest(SlotRequest slotRequest) {
        return slotRequestRepo.save(slotRequest).getId();
    }

    @Override
    public List<SlotRequest> getAllSlotRequests() {
        return slotRequestRepo.findAll();
    }

    @Override
    @Transactional
    public void updateSlotRequestStatus(Long id, String status) {
        slotRequestRepo.updateSlotRequestStatus(id, status);
    }
}
