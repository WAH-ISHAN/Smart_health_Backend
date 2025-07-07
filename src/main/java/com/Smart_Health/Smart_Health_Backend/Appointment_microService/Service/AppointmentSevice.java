package com.Smart_Health.Smart_Health_Backend.Appointment_microService.Service;

import com.Smart_Health.Smart_Health_Backend.Appointment_microService.Data.Appointment;
import com.Smart_Health.Smart_Health_Backend.Appointment_microService.Data.AppointmentRepository;
import com.Smart_Health.Smart_Health_Backend.Notification.Entity.Notification;
import com.Smart_Health.Smart_Health_Backend.Notification.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentSevice {

    @Autowired
    private AppointmentRepository appRepo;

    @Autowired
    private NotificationService notificationService;

    // Get all appointments
    public List<Appointment> getAllAppointments() {
        return appRepo.findAll();
    }

    // Get appointment by ID
    public Optional<Appointment> getAppointmentById(Long id) {
        return appRepo.findById(id);
    }

    // Create new appointment
    public Appointment createAppointment(Appointment appointment) {
        Appointment saved = appRepo.save(appointment);

        // Dummy email fetching logic
        String userEmail = getUserEmailFromUserId(saved.getUserId());

        // Notification message
        String message = "Your appointment is confirmed for " + saved.getAppointmentDate();

        // 1. Save Notification to DB
        Notification notification = new Notification();
        notification.setRecipientEmail(userEmail);
        notification.setMessage(message);
        notification.setType("APPOINTMENT");
        notification.setAppointmentId(saved.getId());
        notificationService.sendNotification(notification);

        // 2. Send Email
        notificationService.sendAppointmentEmail(
                userEmail,
                "Appointment Confirmation",
                message
        );

        return saved;
    }

    // Update existing appointment
    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
        Optional<Appointment> optionalAppointment = appRepo.findById(id);
        if (optionalAppointment.isPresent()) {
            Appointment existing = optionalAppointment.get();
            existing.setUserId(appointmentDetails.getUserId());
            existing.setDoctorId(appointmentDetails.getDoctorId());
            existing.setHospitalId(appointmentDetails.getHospitalId());
            existing.setTimeSlotId(appointmentDetails.getTimeSlotId());
            existing.setAppointmentDate(appointmentDetails.getAppointmentDate());

            Appointment updated = appRepo.save(existing);

            // Optional: send update notification
            String userEmail = getUserEmailFromUserId(updated.getUserId());
            String message = "Your appointment has been updated to " + updated.getAppointmentDate();

            Notification notification = new Notification();
            notification.setRecipientEmail(userEmail);
            notification.setMessage(message);
            notification.setType("APPOINTMENT_UPDATE");
            notification.setAppointmentId(updated.getId());
            notificationService.sendNotification(notification);

            notificationService.sendAppointmentEmail(
                    userEmail,
                    "Appointment Updated",
                    message
            );

            return updated;
        }
        return null;
    }

    // Delete appointment
    public boolean deleteAppointment(Long id) {
        if (appRepo.existsById(id)) {
            appRepo.deleteById(id);
            return true;
        }
        return false;
    }

    // Get all appointments by a specific user
    public List<Appointment> getAppointmentsByUserId(Long userId) {
        return appRepo.findByUserId(userId);
    }

    // Dummy logic - you should later connect to UserService or User table
    private String getUserEmailFromUserId(Long userId) {
        return "user" + userId + "@example.com";
    }
}
