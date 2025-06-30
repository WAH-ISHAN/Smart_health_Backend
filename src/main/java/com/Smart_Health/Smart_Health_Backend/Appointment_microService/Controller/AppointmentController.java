package com.Smart_Health.Smart_Health_Backend.Appointment_microService.Controller;

import com.Smart_Health.Smart_Health_Backend.Appointment_microService.Data.Appointment;
import com.Smart_Health.Smart_Health_Backend.Appointment_microService.Service.AppointmentSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
@CrossOrigin(origins = "http://localhost:5173") // Set this to your React dev server port
public class AppointmentController
{
    @Autowired
    private AppointmentSevice appointmentSevice;

    /* ---------- CRUD ---------- */

    // GET /api/appointments
    @GetMapping("/all")
    public List<Appointment> getAll() {
        return appointmentSevice.getAllAppointments();
    }

    // GET /api/appointments/{id}
    @GetMapping(path= "/{id}")
    public ResponseEntity<Appointment> getById(@PathVariable Long id) {
        return appointmentSevice.getAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/appointments
    @PostMapping
    public ResponseEntity<Appointment> create(@RequestBody Appointment appt) {
        Appointment created = appointmentSevice.createAppointment(appt);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/appointments/{id}
    @PutMapping(path= "/{id}")
    public ResponseEntity<Appointment> update(
            @PathVariable Long id,
            @RequestBody Appointment apptDetails) {

        Appointment updated = appointmentSevice.updateAppointment(id, apptDetails);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    // DELETE /api/appointments/{id}
    @DeleteMapping(path= "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return appointmentSevice.deleteAppointment(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    /* ---------- Query by user ---------- */

    // GET /api/appointments/user/{userId}
    @GetMapping(path= "/user/{userId}")
    public ResponseEntity<List<Appointment>> getByUser(@PathVariable Long userId) {
        List<Appointment> list = appointmentSevice.getAppointmentsByUserId(userId);
        return list.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(list);
    }

}
