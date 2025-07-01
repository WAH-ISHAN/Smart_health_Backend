package com.Smart_Health.Smart_Health_Backend.Doctor_microService.controller;

import com.Smart_Health.Smart_Health_Backend.Doctor_microService.Service.DoctorService;
import com.Smart_Health.Smart_Health_Backend.Doctor_microService.data.Doctors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
@CrossOrigin(origins = "http://localhost:5173") // Your React dev server
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // Get all doctors
    @GetMapping
    public List<Doctors> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    // Get doctor by id
    @GetMapping(path = "/{id}")
    public Doctors getDoctorById(@PathVariable int id) {
        return doctorService.getDoctorById(id);
    }

    // Get doctor by hospital
    @GetMapping(path = "/hospital/{hospital}")
    public ResponseEntity<Doctors> getDoctorByHospital(@PathVariable String hospital) {
        Doctors doctor = doctorService.getDoctorByHospital(hospital);
        if (doctor != null) {
            return ResponseEntity.ok(doctor);
        }
        return ResponseEntity.notFound().build();
    }

    // Get doctors by specialization
    @GetMapping("/speciality/{speciality}")
    public ResponseEntity<List<Doctors>> getDoctorsBySpeciality(@PathVariable String speciality) {
        List<Doctors> doctors = doctorService.getDoctorsBySpeciality(speciality);
        if (!doctors.isEmpty()) {
            return ResponseEntity.ok(doctors);
        }
        return ResponseEntity.notFound().build();
    }

    // Get doctors by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Doctors>> getDoctorsByStatus(@PathVariable String status) {
        List<Doctors> doctors = doctorService.getDoctorsByStatus(status);
        if (!doctors.isEmpty()) {
            return ResponseEntity.ok(doctors);
        }
        return ResponseEntity.notFound().build();
    }

    // Create doctor
    @PostMapping
    public Doctors createDoctor(@RequestBody Doctors doctor) {
        return doctorService.createDoctor(doctor);
    }

    // Update doctor
    @PutMapping(path = "/{id}")
    public ResponseEntity<Doctors> updateDoctor(@PathVariable int id, @RequestBody Doctors doctorDetails) {
        Doctors updatedDoctor = doctorService.updateDoctor(id, doctorDetails);
        if (updatedDoctor != null) {
            return ResponseEntity.ok(updatedDoctor);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete doctor by id
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable int id) {
        boolean deleted = doctorService.deleteDoctor(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
