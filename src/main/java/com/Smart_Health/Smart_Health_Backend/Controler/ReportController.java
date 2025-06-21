package com.Smart_Health.Smart_Health_Backend.Controler;


import com.Smart_Health.Smart_Health_Backend.Entity.Report;
import com.Smart_Health.Smart_Health_Backend.Entity.ReportType;
import com.Smart_Health.Smart_Health_Backend.Services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;
    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }
    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }
    @PostMapping
    public Report createReport(@RequestBody Report report) {
        return reportService.createReport(report);
    }
    @GetMapping("/{id}")
    public Report getReportById(@PathVariable Long id) {
        return reportService.getReportById(id);
    }
    @PutMapping("/{id}")
    public Report updateReport(@PathVariable Long id, @RequestBody Report report) {
        return reportService.updateReport(id, report);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/type/{type}")
    public List<Report> getReportsByType(@PathVariable ReportType type) {
        return reportService.getReportsByType(type);
    }
    @GetMapping("/status/{resolved}")
    public List<Report> getReportsByStatus(@PathVariable boolean resolved) {
        return reportService.getResolvedReports(resolved);
    }
}
