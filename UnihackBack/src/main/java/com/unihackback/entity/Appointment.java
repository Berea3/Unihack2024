package com.unihackback.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Appointment {

    @Id
    private String id;

    private String appointmentName;
    private String appointmentDescription;
    private String status;
    private LocalDate appointmentDate;

    @ManyToOne
    private Case parentCase;

}
