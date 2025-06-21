package com.Smart_Health.Smart_Health_Backend.Report_microService.Services;

import com.Smart_Health.Smart_Health_Backend.Report_microService.Entity.Report;
import com.Smart_Health.Smart_Health_Backend.Report_microService.Entity.ReportType;
import com.Smart_Health.Smart_Health_Backend.Report_microService.Repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }
    public Report createReport(Report report) {
        return reportRepository.save(report);
    }
    public Report getReportById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found with id: " + id));
    }
    public Report updateReport(Long id, Report reportDetails) {
        Report report = getReportById(id);
        report.setType(reportDetails.getTitle());
        report.setDescription(reportDetails.getDescription());
        report.setResolved(reportDetails.isResolved());
        report.setType(reportDetails.getType());
        return reportRepository.save(report);
    }
    public void deleteReport(Long id) {
        Report report = getReportById(id);
        reportRepository.delete(report);
    }
    public List<Report> getReportsByType(ReportType type) {
        return reportRepository.findByType(type);
    }
    public List<Report> getResolvedReports(boolean resolved) {
        return reportRepository.findByResolved(resolved);
    }

}
