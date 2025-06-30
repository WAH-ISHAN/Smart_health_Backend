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

    public List<Appointment> getAllAppointments() {
        return appRepo.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appRepo.findById(id);
    }

    public Appointment createAppointment(Appointment appointment) {
        Appointment saved = appRepo.save(appointment);

        // Send Notification
        Notification notification = new Notification();
        notification.setRecipientEmail(getUserEmailFromUserId(saved.getUserId())); // 🧠 Dummy logic
        notification.setMessage("Your appointment is confirmed for " + saved.getAppointmentDate());
        notification.setType("APPOINTMENT");
        notification.setAppointmentId(saved.getId());

        notificationService.sendNotification(notification);

        return saved;
    }

    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
        Optional<Appointment> optionalAppointment = appRepo.findById(id);
        if (optionalAppointment.isPresent()) {
            Appointment existing = optionalAppointment.get();
            existing.setUserId(appointmentDetails.getUserId());
            existing.setDoctorId(appointmentDetails.getDoctorId());
            existing.setHospitalId(appointmentDetails.getHospitalId());
            existing.setTimeSlotId(appointmentDetails.getTimeSlotId());
            existing.setAppointmentDate(appointmentDetails.getAppointmentDate());
            return appRepo.save(existing);
        }
        return null;
    }

    public boolean deleteAppointment(Long id) {
        if (appRepo.existsById(id)) {
            appRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Appointment> getAppointmentsByUserId(Long userId) {
        return appRepo.findByUserId(userId);
    }

    // Dummy logic (replace with actual UserService/Repository)
    private String getUserEmailFromUserId(Long userId) {
        return "user" + userId + "@example.com";
    }
}
