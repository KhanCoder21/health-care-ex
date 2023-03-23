package com.arsad.entity;

/* Created by Arsad on 2023-03-21 21:57 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "patient_tab")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pat_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "mobile_no")
    private String mobile;

    @Column(name = "date_of_birth")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "current_address")
    private String currentAddress;

    @Column(name = "permanent_address")
    private String permanentAddress;

    @ElementCollection
    @CollectionTable(name = "patient_medical_history_tab", joinColumns = @JoinColumn(name = "patient_med_history_id_fk"))
    @Column(name = "patient_medical_history")
    private Set<String> pastMedicalHistory;

    @Column(name = "if_other_history")
    private String ifOtherHistory;

    @Column(name = "patient_note")
    private String note;
}
