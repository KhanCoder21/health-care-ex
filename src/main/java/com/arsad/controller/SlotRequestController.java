package com.arsad.controller;

import com.arsad.entity.Appointment;
import com.arsad.entity.Patient;
import com.arsad.entity.SlotRequest;
import com.arsad.entity.User;
import com.arsad.service.AppointmentService;
import com.arsad.service.PatientService;
import com.arsad.service.SlotRequestService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/* Created by Arsad on 2023-04-08 23:30 */
@Controller
@RequestMapping("/slots")
public class SlotRequestController {

    @Autowired
    private SlotRequestService slotRequestService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PatientService patientService;

    @GetMapping("/book")
    public String bookSlot(@RequestParam Long appointmentId, HttpSession session, Model model) {
        String message;
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        // Get the current user i . e patient
        User currentUser = (User) session.getAttribute("currentUserSession");
        Patient patient = patientService.getPatientByEmail(currentUser.getUserName());
        // create slot for patient
        SlotRequest slotRequest = new SlotRequest();
        slotRequest.setAppointment(appointment);
        slotRequest.setPatient(patient);
        slotRequest.setStatus("PENDING");
        try {
            Long slotId = slotRequestService.saveSlotRequest(slotRequest);
            if (slotId != null) {
                message = "Patient " + (patient.getFirstName().toUpperCase()) + ", requested appointment slot for the DR. " +
                        (appointment.getDoctor().getFirstName().toUpperCase()) + ", on date " + appointment.getDate() + ", and successfully request is submitted with status " + slotRequest.getStatus();
            } else {
                message = "Sorry, due to technical issue your slot request is failed";
            }
        } catch (Exception exception) {
            message = "Sorry,Booking request already made for this appointment, please check once";
        }
        model.addAttribute("message", message);
        return "slot-request-message";
    }
}
