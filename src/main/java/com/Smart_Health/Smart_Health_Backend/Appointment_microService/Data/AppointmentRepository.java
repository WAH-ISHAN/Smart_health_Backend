package com.Smart_Health.Smart_Health_Backend.Appointment_microService.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository
        extends JpaRepository<Appointment, Integer>
{
    Optional<Appointment> findById(Long id);

    // New method to find appointments by user ID
    List<Appointment> findByUserId(Long userId);
}
