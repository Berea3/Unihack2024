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
}
