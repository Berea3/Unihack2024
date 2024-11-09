package com.unihackback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unihackback.security.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cases")
public class Case {

    @Id
    @Column(name = "id")  // Primary key as String
    private String id;

    @Column(name = "case_name")
    private String caseName;

    @Column(name = "case_description")
    private String caseDescription;

    @Column(name = "case_status")
    private String caseStatus;

    @Column(name = "case_category")
    private String caseCategory;

    @Column(name = "case_date")
    private LocalDate caseDate;

    @Column(name = "case_result")
    private String caseResult;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parentCase", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Report> reports;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "LinkedCases",
            joinColumns = @JoinColumn(name = "case_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<User> users;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parentCase", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    List<Appointment> appointments;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parentCase", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    List<Prescription> prescriptions;

}
