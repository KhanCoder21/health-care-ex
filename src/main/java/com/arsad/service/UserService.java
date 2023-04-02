package com.arsad.service;

import com.arsad.entity.User;

import java.util.Optional;

/* Created by Arsad on 2023-03-25 19:56 */
public interface UserService {

    Long saveUser(User user);

    Optional<User> findUserByName(String userName);

    void updateUserPassword(String pwd, Long userId);
}
