package com.Smart_Health.Smart_Health_Backend.Repository;


import com.Smart_Health.Smart_Health_Backend.Entity.Report;
import com.Smart_Health.Smart_Health_Backend.Entity.ReportType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByResolved(boolean resolved);
    List<Report>findByType(ReportType type);

}
