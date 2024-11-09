package com.unihackback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Prescription {

    @Id
    private String id;

    private String prescriptionName;
    private String prescriptionDescription;

    private String prescriptionResult;

    @ManyToOne
    private Case parentCase;
}
