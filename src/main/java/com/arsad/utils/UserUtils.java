package com.arsad.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

/* Created by Arsad on 2023-03-25 20:10 */
@Component
public class UserUtils {
    public String generatePassword() {
        return String.valueOf(UUID.randomUUID()).replace("-", "").substring(0, 8);
    }

}
