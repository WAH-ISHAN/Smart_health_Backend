package com.Smart_Health.Smart_Health_Backend.Doctor_microService.controller;

import com.Smart_Health.Smart_Health_Backend.Doctor_microService.Service.DoctorService;

import com.Smart_Health.Smart_Health_Backend.Doctor_microService.data.Doctors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
@CrossOrigin(origins = "http://localhost:5173") // Set this to your React dev server port
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // Get all doctors
    @GetMapping
    public List<Doctors> getAllStudent()
    {
        return doctorService.getAllDoctors();
    }

    // Get doctor by id
    @GetMapping(path="/{id}")
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
        return ResponseEntity.notFound().build(); // Return 404 if not found
    }

    // Get doctors by specialization
    @GetMapping("/speciality/{speciality}")
    public ResponseEntity<List<Doctors>> getDoctorsBySpeciality(
            @PathVariable String speciality) {
        List<Doctors> doctors = doctorService.getDoctorsBySpeciality(speciality);
        if (!doctors.isEmpty()) {
            return ResponseEntity.ok(doctors);
        }
        return ResponseEntity.notFound().build();
    }

    // Get doctors by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Doctors>> getDoctorsByStatus(
            @PathVariable String status) {
        List<Doctors> doctors = doctorService.getDoctorsByStatus(status);
        if (!doctors.isEmpty()) {
            return ResponseEntity.ok(doctors);
        }
        return ResponseEntity.notFound().build();
    }

    // Create doctor
    @PostMapping(path="/doctor")
    public Doctors createDoctor(@RequestBody Doctors doctor) {
        return doctorService.createDoctor(doctor);
    }

    // Update doctor
    @PutMapping(path = "/doctor/{id}")
    public ResponseEntity<Doctors> updateDoctor(@PathVariable int id, @RequestBody Doctors doctorDetails) {
        Doctors updatedDoctor = doctorService.updateDoctor(id, doctorDetails);
        if (updatedDoctor != null) {
            return ResponseEntity.ok(updatedDoctor);
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if not found
        }
    }

    // Delete doctor by id
    @DeleteMapping(path = "/doctor/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable int id) {
        boolean deleted = doctorService.deleteDoctor(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build(); // 404 Not Found
    }

}