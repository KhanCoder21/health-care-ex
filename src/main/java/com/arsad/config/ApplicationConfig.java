package com.arsad.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/* Created by Arsad on 2023-03-26 18:08 */
@Configuration
public class ApplicationConfig {

    /**
     * This method is used to create and give BCryptPasswordEncoder object
     *
     * @return CryptPasswordEncoder object
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
