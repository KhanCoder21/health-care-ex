package com.arsad.controller;

import com.arsad.entity.Appointment;
import com.arsad.exception.AppointmentNotFoundException;
import com.arsad.service.AppointmentService;
import com.arsad.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

/* Created by Arsad on 2023-03-24 22:14 */
@Controller
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private DoctorService doctorService;


    /**
     * Method used display the appointment register page
     *
     * @return appointment-register.html page
     */
    @GetMapping("/register")
    public String displayRegister(Model model, @RequestParam(value = "message", required = false) String message) {
        model.addAttribute("message", message);
        model.addAttribute("appointment", new Appointment());
        createDoctorDynamicForUI(model);
        return "appointment-register";
    }


    /**
     * method used to save appointment form and display success or failure message
     *
     * @param appointment appointment the model class
     * @param model       model object to send data back to ui
     * @return register page
     */
    @PostMapping("/save")
    public String saveAppointmentForm(@ModelAttribute Appointment appointment, Model model) {
        String message = null;
        try {
            Long appId = appointmentService.saveAppointment(appointment);
            message = "Appointment record with id " + appId + " is created successfully";
            model.addAttribute("message", message);
            createDoctorDynamicForUI(model);
        } catch (Exception e) {
            e.printStackTrace();
            message = "Sorry, record creation is failed";
            model.addAttribute("message", message);
        }
        return "appointment-register";
    }

    /**
     * Method used to get  all appointments and display in ui
     *
     * @param model   model object usd send data back to ui
     * @param message success or failure message in case of update and delete operation
     * @return
     */
    @GetMapping("/all")
    private String displayAllAppointments(Model model, @RequestParam(value = "message", required = false) String message) {
        try {
            List<Appointment> appointmentList = appointmentService.getAllAppointment();
            model.addAttribute("allAppointments", appointmentList);
            model.addAttribute("message", message);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Sorry, due to technical issue failed fetch all details");
        }
        return "appointment-data";
    }

    /**
     * 4. delete record by id
     *
     * @param id appointment id
     * @return redirect:all
     */
    @GetMapping("/delete")
    public String deleteData(@RequestParam Long id, RedirectAttributes attributes) {
        try {
            appointmentService.removeAppointmentById(id);
            attributes.addAttribute("message", "Record " + id + " is removed successfully");
        } catch (AppointmentNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:all";
    }

    /**
     * 5 . Load data from Db and Show in Edit page
     *
     * @param id id
     * @return appointment-edit or redirect to all
     */
    @GetMapping("/edit")
    public String showEditPage(@RequestParam Long id, Model model, RedirectAttributes attributes) {
        String page = null;
        try {
            Appointment appointment = appointmentService.getAppointmentById(id);
            model.addAttribute("appointment", appointment);
            createDoctorDynamicForUI(model);
            page = "appointment-edit";
        } catch (AppointmentNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
            page = "redirect:all";
        }
        return page;
    }

    /**
     * 6. update record by id
     *
     * @param appointment the appointment
     * @return redirect to all
     */
    @PostMapping("/update")
    public String updateData(@ModelAttribute Appointment appointment, RedirectAttributes attributes) {
        appointmentService.updateAppointment(appointment);
        attributes.addAttribute("message", "Record " + appointment.getId() + " is updated successfully");
        return "redirect:all";
    }


    /**
     * 7. validate spec code
     *
     * @param code code
     * @return response as message
     */
    @GetMapping("/checkEmail")
    @ResponseBody
    public String validateEmailId(@RequestParam String email, @RequestParam Long id) {
        String message = "";
        if (id == 0 && appointmentService.isEmailIdExist(email)) {
            message = email + ", already exist";
        } else if (id != 0 && appointmentService.isEmailIdExistForEdit(email, id)) {
            message = email + ", already exist";
        }
        return message;
    }

    private void createDoctorDynamicForUI(Model model) {
        Map<Long, String> doctors = doctorService.getDocIdAndName();
        model.addAttribute("doctors", doctors);
    }

}