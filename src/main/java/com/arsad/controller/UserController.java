package com.arsad.controller;

import com.arsad.entity.User;
import com.arsad.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
