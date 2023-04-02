package com.arsad.service.impl;

import com.arsad.entity.User;
import com.arsad.repository.UserRepository;
import com.arsad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

/* Created by Arsad on 2023-03-25 20:03 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * This method is used to save the user
     *
     * @param user the user
     * @return id
     */
    @Override
    public Long saveUser(User user) {
        /*encode the user password*/
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user).getId();
    }

    @Override
    @Transactional
    public void updateUserPassword(String password, Long userId) {
        String encodedPassword = passwordEncoder.encode(password);
        userRepository.updateUserPassword(encodedPassword, userId);
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

    /**
     * This method is used to check user given username is there in db or not
     * If there, it will take and convert to spring security user object and return it
     *
     * @param username login username
     * @return UserDetails of spring security
     * @throws UsernameNotFoundException if not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userEntity = findUserByName(username);
        User user = userEntity.orElseThrow(() -> new UsernameNotFoundException(username));
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getUserRole())));
    }
}
