package com.arsad.entity;

import jakarta.persistence.*;
import lombok.Data;

/* Created by Arsad on 2023-04-08 22:32 */
@Data
@Entity
@Table(name = "slot_request_tab", uniqueConstraints = {@UniqueConstraint(columnNames = {"app_id_fk", "patient_id_fk"})})
public class SlotRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "slot_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "app_id_fk")
    private Appointment appointment;

    @OneToOne
    @JoinColumn(name = "patient_id_fk")
    private Patient patient;

    private String status;
}
