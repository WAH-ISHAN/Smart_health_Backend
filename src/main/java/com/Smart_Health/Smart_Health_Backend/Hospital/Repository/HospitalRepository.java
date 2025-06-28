package com.Smart_Health.Smart_Health_Backend.Hospital.Repository;

import com.Smart_Health.Smart_Health_Backend.Hospital.Entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

}
