package com.Smart_Health.Smart_Health_Backend.Appointment_microService.Service;

import com.Smart_Health.Smart_Health_Backend.Appointment_microService.Data.Appointment;
import com.Smart_Health.Smart_Health_Backend.Appointment_microService.Data.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService
{
    @Autowired
    private AppointmentRepository appRepo;

    public List<Appointment> getAllAppointments() {
        return appRepo.findAll();
    }

    // Get appointment by ID
    public Optional<Appointment> getAppointmentById(Long id) {
        return appRepo.findById(id);
    }

    // Creating appointment
    public Appointment createAppointment(Appointment appointment)
    {
        return appRepo.save(appointment);
    }

    //update appointment
    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
        Optional<Appointment> optionalAppointment = appRepo.findById(id);
        if (optionalAppointment.isPresent()) {
            Appointment existingAppointment = optionalAppointment.get();
            // Update fields
            existingAppointment.setUserId(appointmentDetails.getUserId());
            existingAppointment.setDoctorId(appointmentDetails.getDoctorId());
            existingAppointment.setHospitalId(appointmentDetails.getHospitalId());
            existingAppointment.setTimeSlotId(appointmentDetails.getTimeSlotId());
            existingAppointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
            return appRepo.save(existingAppointment);
        }
        return null; //if the appointment does not exist
    }

    //Delete appointment
    public boolean deleteAppointment(Long id) {
        Optional<Appointment> optionalAppointment = appRepo.findById(id);
        if (optionalAppointment.isPresent()) {
            appRepo.deleteById(Math.toIntExact(id));
            return true; // Successfully deleted
        }
        return false; // Appointment not found
    }

    public List<Appointment> getAppointmentsByUserId(Long userId) {
        return appRepo.findByUserId(userId);
    }
}
