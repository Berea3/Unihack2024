package com.unihackback.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unihackback.security.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "case")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "case_id")
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

    @Column(name = "case_result")
    String caseResult;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "report_id", referencedColumnName = "case_id", insertable = false, updatable = false)
    List<Report> Reports;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "case_id", referencedColumnName = "id", insertable = false, updatable = false)
    User user;

}
