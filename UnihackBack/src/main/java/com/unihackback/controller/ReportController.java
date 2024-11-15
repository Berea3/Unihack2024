package com.unihackback.controller;

import com.unihackback.entity.Case;
import com.unihackback.entity.Report;
import com.unihackback.entity.generator.Generator;
import com.unihackback.repository.CaseRepository;
import com.unihackback.repository.ReportRepository;
import com.unihackback.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController()
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @Autowired
    CaseRepository caseRepository;

    @PostMapping("/create/{caseId}")
    public void create(@RequestBody Report report, @PathVariable String caseId)
    {
        System.out.println("Creating report" + caseId);

        Case pacientCase=caseRepository.findById(caseId).get();
        report.setReportId(Generator.generateId());
        pacientCase.addReport(report);
        caseRepository.save(pacientCase);
    }

    @GetMapping(value = "/report/{reportId}", produces = "application/json")
    public Report getReportById(@PathVariable String reportId) {
        return reportService.getReportById(reportId);
    }

    @GetMapping(value = "/reports-by-case/{caseId}", produces = "application/json")
    public List<Report> getReportsByCase(@PathVariable String caseId) {
        return reportService.getReportsByCase(caseId);
    }

    @PostMapping(value = "/add-report", consumes = "application/json")
    public void saveReport(Report report) {
        reportService.saveReport(report);
    }

    @GetMapping(value = "/reports-by-patient/{id}", produces = "application/json")
    public List<Report> getReportsByPatient(@PathVariable String id) {
        return reportService.getReportsByPatient(id);
    }

    @GetMapping(value = "/latest-report-by-user/{id}", produces = "application/json")
    public Report getLatestReportByUser(@PathVariable String id) {
        return reportRepository.getLatestReportByUserId(id);
    }

//    @GetMapping(value = "/reports-by-doctor", produces = "application/json")
//    public List<Report> getReportsByDoctor(String doctorId)
//
//    }
}
