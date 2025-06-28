package com.Smart_Health.Smart_Health_Backend.Doctor_microService.Service;

import com.Smart_Health.Smart_Health_Backend.Doctor_microService.data.DoctorRepository;
import com.Smart_Health.Smart_Health_Backend.Doctor_microService.data.Doctors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService
{
    @Autowired
    private DoctorRepository docRepo;

    // Get all doctors
    public List<Doctors> getAllDoctors() {
        return docRepo.findAll();
    }

    // Get doctor by id
    public Doctors getDoctorById(int id) {
        Optional<Doctors> doc =
                docRepo.findById(id);
        if(doc.isPresent()){
            return doc.get();
        }
        return null;
    }

    // Get doctor by hospital
    public Doctors getDoctorByHospital(String hospital) {
        Optional<Doctors> doc = docRepo.findByHospital(hospital);
        return doc.orElse(null); // Return null if not found
    }

    // Get doctors by specialization
    public List<Doctors> getDoctorsBySpeciality(String speciality) {
        return docRepo.findBySpeciality(speciality);
    }

    // Get doctors by status
    public List<Doctors> getDoctorsByStatus(String status) {
        return docRepo.findByStatus(status);
    }

    // Create doctor
    public Doctors createDoctor(Doctors doctor) {
        return docRepo.save(doctor);
    }

    // Update doctor
    public Doctors updateDoctor(int id, Doctors doctorDetails) {
        Optional<Doctors> optionalDoctor = docRepo.findById(id);
        if (optionalDoctor.isPresent()) {
            Doctors existingDoctor = optionalDoctor.get();
            existingDoctor.setName(doctorDetails.getName());
            existingDoctor.setGender(doctorDetails.getGender());
            existingDoctor.setSpeciality(doctorDetails.getSpeciality());
            existingDoctor.setHospital(doctorDetails.getHospital());
            return docRepo.save(existingDoctor);
        }
        return null; // Return null if the doctor does not exist
    }

    // Delete doctor by id
    public boolean deleteDoctor(int id) {
        Optional<Doctors> doctor = docRepo.findById(id);
        if (doctor.isPresent()) {
            docRepo.deleteById(id);
            return true;
        }
        return false;
    }



}
