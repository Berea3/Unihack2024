package com.unihackback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Report {

    @Id
    @Column(name = "id")
    private String reportId;  // Primary key as String (set manually or using UUID)
    private String reportName;
    private String reportDescription;
    private String reportPriority;
    private LocalDate reportDate;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "case_id", referencedColumnName = "id")  // Ensure it matches the primary key in Case
    private Case parentCase;
}
