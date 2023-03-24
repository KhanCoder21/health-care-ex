package com.arsad.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/* Created by Arsad on 2023-03-24 21:59 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointment_tab")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "app_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "app_doc_id_fk")
    private Doctor doctor;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    @Column(name = "app_date")
    private Date date;
    @Column(name = "app_slots")
    private Integer noOfSlots;
    @Column(name = "app_details")
    private String details;
    @Column(name = "app_fee")
    private Double amount;
}
