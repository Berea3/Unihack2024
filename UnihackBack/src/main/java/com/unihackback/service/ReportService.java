package com.unihackback.service;

import com.unihackback.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unihackback.entity.Report;

import javax.imageio.spi.ServiceRegistry;
import java.util.List;

@Service
public class ReportService {

    ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Report getReportById(String reportId) {
        return reportRepository.findById(reportId).orElse(null);
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public List<Report> getReportsByCase(String caseId) {
        return reportRepository.findByCaseId(caseId);
    }

    public void saveReport(Report report) {
        reportRepository.save(report);
    }
}
