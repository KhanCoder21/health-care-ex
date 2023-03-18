package com.arsad.controller;

import com.arsad.entity.Doctor;
import com.arsad.exception.DoctorNotFoundException;
import com.arsad.exception.SpecializationNotFoundException;
import com.arsad.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/* Created by Arsad on 2023-03-18 12:07 */
@Controller
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService service;

    /**
     * Method used display the doctor register page
     *
     * @return doctor-register.html page
     */
    @GetMapping("/register")
    public String displayRegister() {
        return "doctor-register";
    }

    /**
     * method used to save doctor form and display success or failure message
     *
     * @param doctor doctor the model class
     * @param model  model object to send data back to ui
     * @return register page
     */
    @PostMapping("/save")
    public String saveDoctorForm(@ModelAttribute Doctor doctor, Model model) {
        String message = null;
        try {
            Long docId = service.saveDoctor(doctor);
            message = "Record " + docId + " is created successfully";
            model.addAttribute("message", message);
        } catch (Exception e) {
            e.printStackTrace();
            message = "Sorry, record creation is failed";
            model.addAttribute("message", message);
        }
        return "doctor-register";
    }

    /**
     * Method used to get all doctors and display in ui
     *
     * @param model   model object usd send data back to ui
     * @param message success or failure message in case of update and delete operation
     * @return
     */
    @GetMapping("/all")
    private String displayAllDoctors(Model model, @RequestParam(value = "message", required = false) String message) {
        List<Doctor> allDoctors = service.getAllDoctor();
        model.addAttribute("allDoctors", allDoctors);
        model.addAttribute("message", message);
        return "doctor-data";
    }

    /**
     * 4. delete record by id
     *
     * @param id doctor id
     * @return redirect:all
     */
    @GetMapping("/delete")
    public String deleteData(@RequestParam Long id, RedirectAttributes attributes) {
        try {
            service.removeDoctorById(id);
            attributes.addAttribute("message", "Record " + id + " is removed successfully");
        } catch (DoctorNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:all";
    }

    /**
     * 5 . Load data from Db and Show in Edit page
     *
     * @param id id
     * @return doctor-edit or redirect to all
     */
    @GetMapping("/edit")
    public String showEditPage(@RequestParam Long id, Model model, RedirectAttributes attributes) {
        String page = null;
        try {
            Doctor doctor = service.getDoctorById(id);
            model.addAttribute("doctor", doctor);
            page = "doctor-edit";
        } catch (DoctorNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
            page = "redirect:all";
        }
        return page;
    }

    /**
     * 6. update record by id
     *
     * @param id id
     * @return redirect to all
     */
    @PostMapping("/update")
    public String updateData(@ModelAttribute Doctor doctor, RedirectAttributes attributes) {
        service.updateDoctor(doctor);
        attributes.addAttribute("message", "Record " + doctor.getId() + " is updated successfully");
        return "redirect:all";
    }


}
