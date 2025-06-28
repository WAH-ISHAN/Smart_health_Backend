package com.Smart_Health.Smart_Health_Backend.Appointment_microService.Controller;

import com.Smart_Health.Smart_Health_Backend.Appointment_microService.Data.Appointment;
import com.Smart_Health.Smart_Health_Backend.Appointment_microService.Service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")          // <<< single, clear base path
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    /* ---------- CRUD ---------- */

    // GET /api/appointments
    @GetMapping
    public List<Appointment> getAll() {
        return appointmentService.getAllAppointments();
    }

    // GET /api/appointments/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/appointments
    @PostMapping
    public ResponseEntity<Appointment> create(@RequestBody Appointment appt) {
        Appointment created = appointmentService.createAppointment(appt);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/appointments/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> update(
            @PathVariable Long id,
            @RequestBody Appointment apptDetails) {

        Appointment updated = appointmentService.updateAppointment(id, apptDetails);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    // DELETE /api/appointments/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return appointmentService.deleteAppointment(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    /* ---------- Query by user ---------- */

    // GET /api/appointments/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Appointment>> getByUser(@PathVariable Long userId) {
        List<Appointment> list = appointmentService.getAppointmentsByUserId(userId);
        return list.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(list);
    }
}
