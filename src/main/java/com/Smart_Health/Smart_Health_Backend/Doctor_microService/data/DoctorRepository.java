package com.Smart_Health.Smart_Health_Backend.Doctor_microService.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository
        extends JpaRepository<Doctors, Integer>
{
    // Method to find a doctor by hospital
    Optional<Doctors> findByHospital(String hospital);

    // Method to find doctors by specialization
    List<Doctors> findBySpeciality(String speciality);

    // Method to find doctors by status
    List<Doctors> findByStatus(String status);
}
