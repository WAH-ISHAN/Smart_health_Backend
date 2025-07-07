// DoctorController.java
package com.Smart_Health.Smart_Health_Backend.Doctor_microService.controller;

import com.Smart_Health.Smart_Health_Backend.Doctor_microService.Service.DoctorService;
import com.Smart_Health.Smart_Health_Backend.Doctor_microService.data.Doctors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
@CrossOrigin(origins = "http://localhost:5173")   // React dev server
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // ── All doctors ───────────────────────────────
    @GetMapping
    public List<Doctors> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    // ── By id ─────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<Doctors> getDoctorById(@PathVariable int id) {
        Doctors doc = doctorService.getDoctorById(id);
        return (doc != null) ? ResponseEntity.ok(doc) : ResponseEntity.notFound().build();
    }

    // ── By hospital  → **return list** ────────────
    @GetMapping("/hospital/{hospital}")
    public ResponseEntity<List<Doctors>> getDoctorsByHospital(@PathVariable String hospital) {
        List<Doctors> doctors = doctorService.getDoctorsByHospital(hospital);
        return doctors.isEmpty() ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(doctors);
    }

    // ── By speciality ─────────────────────────────
    @GetMapping("/speciality/{speciality}")
    public ResponseEntity<List<Doctors>> getDoctorsBySpeciality(@PathVariable String speciality) {
        List<Doctors> doctors = doctorService.getDoctorsBySpeciality(speciality);
        return doctors.isEmpty() ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(doctors);
    }

    // ── By status ─────────────────────────────────
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Doctors>> getDoctorsByStatus(@PathVariable String status) {
        List<Doctors> doctors = doctorService.getDoctorsByStatus(status);
        return doctors.isEmpty() ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(doctors);
    }

    // ── Create ────────────────────────────────────
    @PostMapping
    public Doctors createDoctor(@RequestBody Doctors doctor) {
        return doctorService.createDoctor(doctor);
    }

    // ── Update ────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<Doctors> updateDoctor(@PathVariable int id, @RequestBody Doctors doctorDetails) {
        Doctors updated = doctorService.updateDoctor(id, doctorDetails);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // ── Delete ────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable int id) {
        return doctorService.deleteDoctor(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
