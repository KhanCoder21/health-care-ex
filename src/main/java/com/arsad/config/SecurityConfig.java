package com.arsad.config;

import com.arsad.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by Arsad on 2023-03-26 18:40
 * ref docs : <a href="https://javatechonline.com/spring-security-userdetailsservice-using-spring-boot-3/">...</a>
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;

    public SecurityConfig() {
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/patient/register", "/patient/save").permitAll()
                .requestMatchers("patient/all").hasAuthority(UserRole.ADMIN.name())
                .requestMatchers("/spec/**").hasAuthority(UserRole.ADMIN.name())
                .requestMatchers("/doctor/**").hasAuthority(UserRole.ADMIN.name())
                .requestMatchers("/appointment/**").hasAuthority(UserRole.ADMIN.name())
                .requestMatchers("/appointment/view", "/appointment/viewSlots").hasAuthority(UserRole.PATIENT.name())
                .requestMatchers("user/login", "/login").permitAll()

                .anyRequest().authenticated().and().formLogin().loginPage("/user/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/user/setup", true)
                .failureUrl("/user/login?error=true")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/user/login?logout=true");

        return http.build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }
}
