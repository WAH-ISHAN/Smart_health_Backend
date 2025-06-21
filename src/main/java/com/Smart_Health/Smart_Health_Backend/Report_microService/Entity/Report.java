package com.Smart_Health.Smart_Health_Backend.Report_microService.Entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Description;

    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "create_date")
    private LocalDateTime createdDate;

    @Column(name = "is_resolved")
    private boolean resolved;

    @Enumerated(EnumType.STRING)
    private ReportType type;

    @Column(name = "reporter_name")
    private String reporterName;



    //Constructor
    public Report() {

    }

    public Report(Long id, String description, Long patientId, Long doctorId, LocalDateTime createdDate, boolean resolved, ReportType type, String reporterName) {
        this.id = id;
        Description = description;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.createdDate = createdDate;
        this.resolved = resolved;
        this.type = type;
        this.reporterName = reporterName;
    }


    //Get and Set method eka denwa


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public ReportType getType() {
        return type;
    }

    public void setType(ReportType type) {
        this.type = type;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public ReportType getTitle() {
        return type;
    }
}
