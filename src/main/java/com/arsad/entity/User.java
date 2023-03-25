package com.arsad.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* Created by Arsad on 2023-03-25 19:47 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_tab")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_display_name")
    private String displayName;
    @Column(name = "user_name")
    private String userName; // this email id
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_role")
    private String userRole;
}
