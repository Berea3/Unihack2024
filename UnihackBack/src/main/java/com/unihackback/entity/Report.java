package com.unihackback.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Table(name = "report")
@Entity
public class Report {

    @Id
    @Column(name = "report_id")
    private String reportId;

    @Column(name = "report_name")
    private String reportName;

    @Column(name = "report_description")
    private String reportDescription;

    @Column(name = "report_priority")
    private String reportPriority;

    @Column(name = "report_date")
    private LocalDate reportDate;

}
