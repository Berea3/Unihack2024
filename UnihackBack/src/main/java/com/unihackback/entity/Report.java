package com.unihackback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
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

    @Override
    public String toString() {
        return "Report{" +
                "reportId='" + reportId + '\'' +
                ", reportName='" + reportName + '\'' +
                ", reportDescription='" + reportDescription + '\'' +
                ", reportPriority='" + reportPriority + '\'' +
                ", reportDate=" + reportDate +
                ", parentCase=" + parentCase +
                '}';
    }
}
