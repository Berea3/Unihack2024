package com.unihackback.repository;

import com.unihackback.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, String> {

    @Query(value = "select report from Report report where report.parentCase = ?1")
    List<Report> findByCaseId(String caseId);

    @Query("SELECT r FROM Report r WHERE r.parentCase.id = :caseId ORDER BY r.reportDate DESC LIMIT 1")
    Report findLatestReportByCaseId(String caseId);

    @Query("SELECT r FROM Report r " +
            "JOIN r.parentCase c " +
            "JOIN c.users u " +
            "WHERE u.id = :id " +
            "ORDER BY r.reportDate DESC LIMIT 1")
    Report getLatestReportByUserId(String id);

}
