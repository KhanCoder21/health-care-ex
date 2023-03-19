package com.arsad.controller;

import com.arsad.entity.Specialization;
import com.arsad.exception.SpecializationNotFoundException;
import com.arsad.reports.SpecializationExcelView;
import com.arsad.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/spec")
public class SpecializationController {

    @Autowired
    private SpecializationService service;

    /**
     * 1. show register page
     */
    @GetMapping("/register")
    public String displayRegister() {
        return "specialization-register";
    }

    /**
     * 2. On submit from save data
     *
     * @param specialization specialization
     * @param model          model
     * @return specialization-register.html
     */
    @PostMapping("/save")
    public String saveRegisterForm(@ModelAttribute Specialization specialization, Model model) {
        try {
            Long specId = service.saveSpecialization(specialization);
            String message = "Record " + specId + " is created successfully";
            model.addAttribute("message", message);
        } catch (Exception e) {
            String message = "Record creation failed";
            model.addAttribute("message", message);
        }
        return "specialization-register";
    }

    /**
     * 3. display all SpeciaSpecializationlization
     *
     * @param model model
     * @return specialization-data.html
     */
    @GetMapping("/all")
    public String viewAllSpecialization(Model model,
                                        @RequestParam(value = "message", required = false) String message) {
        List<Specialization> allSpecialization = service.getAllSpecialization();
        model.addAttribute("allSpecialization", allSpecialization);
        model.addAttribute("message", message);
        return "specialization-data";
    }

    /**
     * 4. delete record by id
     *
     * @param id id
     * @return redirect:all
     */
    @GetMapping("/delete")
    public String deleteData(@RequestParam Long id, RedirectAttributes attributes) {
        try {
            service.removeSpecializationById(id);
            attributes.addAttribute("message", "Record " + id + " is removed successfully");
        } catch (SpecializationNotFoundException e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:all";
    }

    /**
     * 5 . Load data from Db and Show in Edit page
     *
     * @param id id
     * @return specialization-edit or redirect to all
     */
    @GetMapping("/edit")
    public String showEditPage(@RequestParam Long id, Model model, RedirectAttributes attributes) {
        String page = null;
        try {
            Specialization spec = service.getSpecializationById(id);
            model.addAttribute("specialization", spec);
            page = "specialization-edit";
        } catch (SpecializationNotFoundException e) {
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
    public String updateData(@ModelAttribute Specialization specialization, RedirectAttributes attributes) {
        service.updateSpecialization(specialization);
        attributes.addAttribute("message", "Record " + specialization.getId() + " is updated successfully");
        return "redirect:all";
    }

    /**
     * 7. validate spec code
     *
     * @param code code
     * @return response as message
     */
    @GetMapping("/checkcode")
    @ResponseBody
    public String validateSpecCode(@RequestParam String code, @RequestParam Long id) {
        String message = "";
        if (id == 0 && service.isSpecCodeExist(code)) {
            message = code + ", already exist";
        } else if (id != 0 && service.isSpecCodeExistForEdit(code, id)) {
            message = code + ", already exist";
        }
        return message;
    }

    @GetMapping("/excel")
    public ModelAndView exportSpecToExcel() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView(new SpecializationExcelView());
        // read data from database base send to excel class
        List<Specialization> specList = service.getAllSpecialization();
        modelAndView.addObject("specList", specList);
        return modelAndView;
    }
}
