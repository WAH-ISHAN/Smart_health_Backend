package com.Smart_Health.Smart_Health_Backend.Hospital.Repository;

import com.Smart_Health.Smart_Health_Backend.Hospital.Entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    @Query("SELECT h FROM Hospital h WHERE LOWER(h.name) LIKE CONCAT('%', :search, '%') OR LOWER(h.location) LIKE CONCAT('%', :search, '%')")
    List<Hospital> searchHospitals(@Param("search") String search);
}
