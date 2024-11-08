package com.unihackback.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;

@Entity
@Table(name = "case")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String caseId;

    @Column(name = "case_name")
    private String caseName;

    @Column(name = "case_description")
    private String caseDescription;

    @Column(name = "case_status")
    private String caseStatus;

    @Column(name = "case_category")
    private String caseCategory;

    @Column(name = "case_date")
    private String caseDate;


}
