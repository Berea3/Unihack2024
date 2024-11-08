package com.unihackback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unihackback.security.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private String caseDate;

    @Column(name = "case_result")
    private String caseResult;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parentCase", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Report> reports;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName = "id")  // Ensure the user column matches the foreign key
    private User user;
}
