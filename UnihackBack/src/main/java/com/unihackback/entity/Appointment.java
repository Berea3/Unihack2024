package com.unihackback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Appointment {

    @Id
    private String id;

    private String appointmentName;
    private String appointmentDescription;

    @ManyToOne
    private Case parentCase;

}
