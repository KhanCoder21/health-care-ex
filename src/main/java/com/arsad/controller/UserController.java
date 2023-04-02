package com.arsad.controller;

import com.arsad.entity.User;
import com.arsad.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

/* Created by Arsad on 2023-04-02 00:02 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLogin() {
        return "user-login";
    }

    @GetMapping("/showPwdUpdate")
    public String showPasswordUpdate() {
        return "user-password-update";
    }

    @PostMapping("/pwdUpdate")
    public String updateUserPassword(@RequestParam String password, HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUserSession");
        userService.updateUserPassword(password, user.getId());
        model.addAttribute("message", "Password updated successfully");
        return "user-password-update";
    }

    @GetMapping("/setup")
    public String userSetup(HttpSession session, Principal principal) {
        try {
            /*Read current username*/
            String currentUserEmail = principal.getName();
            /*Load current user object from database*/
            User currentUser = userService.findUserByName(currentUserEmail).get();
            /*Store full info of current user in http session for global access*/
            session.setAttribute("currentUserSession", currentUser);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "user-home";
    }
}
