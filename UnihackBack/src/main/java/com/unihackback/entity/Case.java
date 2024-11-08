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
//    @Column(name = "case_id")
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
    String caseResult;

//    @JsonIgnore
//    @OneToMany(fetch = FetchType.EAGER,mappedBy = "parentCase", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
//    private List<Report> reports;


    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "parentCase", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Report> reports;


    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

}
