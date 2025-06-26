package com.Smart_Health.Smart_Health_Backend.Report_microService.Services;

import com.Smart_Health.Smart_Health_Backend.Report_microService.Entity.Report;
import com.Smart_Health.Smart_Health_Backend.Report_microService.Repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository repo;

    public Report saveReport(MultipartFile file, String uploadedBy) throws Exception {
        Report report = new Report();
        report.setFilename(file.getOriginalFilename());
        report.setFiletype(file.getContentType());
        report.setUploadedBy(uploadedBy);
        report.setUploadDate(LocalDateTime.now());
        report.setData(file.getBytes());
        return repo.save(report);
    }

    public Optional<Report> getReport(Long id) {
        return repo.findById(id);
    }

    public List<Report> getAllReports() {
        return repo.findAll();
    }
}