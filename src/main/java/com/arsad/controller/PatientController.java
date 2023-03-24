package com.arsad.controller;

import com.arsad.entity.Patient;
import com.arsad.exception.PatientNotFoundException;
import com.arsad.service.PatientService;
import com.arsad.service.SpecializationService;
import com.arsad.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/* Created by Arsad on 2023-03-18 12:07 */
@Controller
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    EmailUtils emailUtils;
    @Autowired
    private PatientService patientService;
    @Autowired
    private SpecializationService specializationService;

    /**
     * Method used display the patient register page
     *
     * @return patient-register.html page
     */
    @GetMapping("/register")
    public String displayRegister(Model model, @RequestParam(value = "message", required = false) String message) {
        model.addAttribute("message", message);
        model.addAttribute("Patient", new Patient());
        return "patient-register";
    }

    /**
     * method used to save patient form and display success or failure message
     *
     * @param patient patient the model class
     * @param model   model object to send data back to ui
     * @return register page
     */
    @PostMapping("/save")
    public String savePatientForm(@ModelAttribute Patient patient, Model model) {
        String message;
        try {
            Long docId = patientService.savePatient(patient);
            message = "Record " + docId + " is created successfully";
            model.addAttribute("message", message);
            if (null != docId) {
                System.out.println("##### Mail sent successfully");
                /* String finalMessage = message;
                 new Thread(() -> emailUtils.sendMail("abc@gmail.com", patient.getEmail(), "Registration success", finalMessage, null)).start();*/
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "Sorry, record creation is failed";
            model.addAttribute("message", message);
        }
        return "patient-register";
    }

    /**
     * Method used to get all patients and display in ui
     *
     * @param model   model object usd send data back to ui
     * @param message success or failure message in case of update and delete operation
     * @return all patient page
     */
    @GetMapping("/all")
    private String displayAllPatients(Model model, @RequestParam(value = "message", required = false) String message) {
        List<Patient> allPatients = patientService.getAllPatient();
        model.addAttribute("allPatients", allPatients);
        model.addAttribute("message", message);
        return "patient-data";
    }

    /**
     * 4. delete record by id
     *
     * @param id patient id
     * @return redirect:all
     */
    @GetMapping("/delete")
    public String deleteData(@RequestParam Long id, RedirectAttributes attributes) {
        try {
            patientService.removePatientById(id);
            attributes.addAttribute("message", "Record " + id + " is removed successfully");
        } catch (PatientNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:all";
    }

    /**
     * 5 . Load data from Db and Show in Edit page
     *
     * @param id id
     * @return patient-edit or redirect to all
     */
    @GetMapping("/edit")
    public String showEditPage(@RequestParam Long id, Model model, RedirectAttributes attributes) {
        String page;
        try {
            Patient patient = patientService.getPatientById(id);
            model.addAttribute("patient", patient);
            page = "patient-edit";
        } catch (PatientNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
            page = "redirect:all";
        }
        return page;
    }

    /**
     * 6. update record by id
     *
     * @param patient the patient
     * @return redirect to all
     */
    @PostMapping("/update")
    public String updateData(@ModelAttribute Patient patient, RedirectAttributes attributes) {
        patientService.updatePatient(patient);
        attributes.addAttribute("message", "Record " + patient.getId() + " is updated successfully");
        return "redirect:all";
    }


    /**
     * 7. validate email id
     *
     * @param email email
     * @return response as message
     */
    @GetMapping("/checkEmail")
    @ResponseBody
    public String validateEmailId(@RequestParam String email, @RequestParam Long id) {
        String message = "";
        if (id == 0 && patientService.isEmailIdExist(email)) {
            message = email + ", already exist";
        } else if (id != 0 && patientService.isEmailIdExistForEdit(email, id)) {
            message = email + ", already exist";
        }
        return message;
    }

}
