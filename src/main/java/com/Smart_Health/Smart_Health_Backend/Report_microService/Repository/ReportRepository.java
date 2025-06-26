package com.Smart_Health.Smart_Health_Backend.Report_microService.Repository;

import com.Smart_Health.Smart_Health_Backend.Report_microService.Entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}