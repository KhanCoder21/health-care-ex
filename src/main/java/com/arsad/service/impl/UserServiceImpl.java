package com.arsad.service.impl;

import com.arsad.entity.User;
import com.arsad.repository.UserRepository;
import com.arsad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/* Created by Arsad on 2023-03-25 20:03 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * This method is used to save the user
     *
     * @param user the user
     * @return id
     */
    @Override
    public Long saveUser(User user) {
        return userRepository.save(user).getId();
    }

    /**
     * This method is used to find the user based on name(email)
     *
     * @param userName the username
     * @return user if found
     */
    @Override
    public Optional<User> findUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
