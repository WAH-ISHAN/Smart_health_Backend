package com.Smart_Health.Smart_Health_Backend.Report_microService.Controler;

import com.Smart_Health.Smart_Health_Backend.Report_microService.Entity.Report;
import com.Smart_Health.Smart_Health_Backend.Report_microService.Services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reports")
@CrossOrigin(origins = "http://localhost:3000") // Allow React frontend access
public class ReportController {

    @Autowired
    private ReportService reportService;

    // POST /reports/upload — Upload report with file and metadata
    @PostMapping("/upload")
    public ResponseEntity<String> uploadReport(
            @RequestParam("file") MultipartFile file,
            @RequestParam("uploadedBy") String uploadedBy) {
        try {
            reportService.saveReport(file, uploadedBy);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload report: " + e.getMessage());
        }
    }

    // OPTIONAL: POST /reports — handle direct upload if frontend mistakenly uses /reports
    @PostMapping
    public ResponseEntity<String> handleDefaultPost(
            @RequestParam("file") MultipartFile file,
            @RequestParam("uploadedBy") String uploadedBy) {
        try {
            reportService.saveReport(file, uploadedBy);
            return ResponseEntity.ok("File uploaded successfully via /reports");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload report: " + e.getMessage());
        }
    }

    // GET /reports/download/{id} — Download specific report file
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> getReportById(@PathVariable Long id) {
        Optional<Report> reportOpt = reportService.getReport(id);

        if (reportOpt.isPresent()) {
            Report report = reportOpt.get();

            String filetype = report.getFiletype();
            if (filetype == null || filetype.isEmpty()) {
                filetype = "application/octet-stream"; // fallback
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(filetype));
            headers.setContentDisposition(ContentDisposition.attachment()
                    .filename(report.getFilename())
                    .build());

            return new ResponseEntity<>(report.getData(), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // GET /reports — List all reports (without file content)
    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        List<Report> reports = reportService.getAllReports();
        // Clear byte[] to reduce payload
        reports.forEach(report -> report.setData(null));
        return ResponseEntity.ok(reports);
    }
}
