package com.unihackback.controller;

import com.unihackback.entity.Report;
import com.unihackback.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController("/report")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping(value = "/report/{reportId}", produces = "application/json")
    public Report getReportById(@PathVariable String reportId) {
        return reportService.getReportById(reportId);
    }

    @GetMapping(value = "/reports-by-case/{caseId}", produces = "application/json")
    public List<Report> getReportsByCase(@PathVariable String caseId) {
        return reportService.getReportsByCase(caseId);
    }

    @PostMapping(value = "/report", consumes = "application/json")
    public void saveReport(Report report) {
        reportService.saveReport(report);
    }

//    @GetMapping(value = "/reports-by-doctor", produces = "application/json")
//    public List<Report> getReportsByDoctor(String doctorId)
//
//    }
}
