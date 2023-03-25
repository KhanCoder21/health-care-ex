package com.arsad.runner;

import com.arsad.entity.User;
import com.arsad.enums.UserRole;
import com.arsad.service.UserService;
import com.arsad.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/* Created by Arsad on 2023-03-25 21:37 */
@Component
public class MasterAccountSetupRunner implements CommandLineRunner {

    @Value("${master.user.name}")
    private String displayName;
    @Value("${master.user.email}")
    private String userName;
    @Autowired
    private UserService userService;
    @Autowired
    private UserUtils userUtils;

    /**
     * @param args args
     * @throws Exception exception
     */
    @Override
    public void run(String... args) throws Exception {
        if (!userService.findUserByName(userName).isPresent()) {
            User user = new User();
            user.setDisplayName(displayName);
            user.setUserName(userName);
            user.setPassword(userUtils.generatePassword());
            user.setUserRole(UserRole.ADMIN.name());
            userService.saveUser(user);
            /* TODO : Email part is pending */
        }

    }
}
