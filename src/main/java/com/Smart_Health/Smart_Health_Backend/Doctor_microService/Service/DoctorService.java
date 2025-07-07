// DoctorService.java
package com.Smart_Health.Smart_Health_Backend.Doctor_microService.Service;

import com.Smart_Health.Smart_Health_Backend.Doctor_microService.data.DoctorRepository;
import com.Smart_Health.Smart_Health_Backend.Doctor_microService.data.Doctors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository docRepo;

    public List<Doctors> getAllDoctors() {
        return docRepo.findAll();
    }

    public Doctors getDoctorById(int id) {
        return docRepo.findById(id).orElse(null);
    }

    // *** NEW : return list  ***
    public List<Doctors> getDoctorsByHospital(String hospital) {
        return docRepo.findAllByHospitalIgnoreCase(hospital);
    }

    public List<Doctors> getDoctorsBySpeciality(String speciality) {
        return docRepo.findBySpecialityIgnoreCase(speciality);
    }

    public List<Doctors> getDoctorsByStatus(String status) {
        return docRepo.findByStatusIgnoreCase(status);
    }

    public Doctors createDoctor(Doctors doctor) {
        return docRepo.save(doctor);
    }

    public Doctors updateDoctor(int id, Doctors doctorDetails) {
        return docRepo.findById(id).map(existing -> {
            existing.setName(doctorDetails.getName());
            existing.setGender(doctorDetails.getGender());
            existing.setSpeciality(doctorDetails.getSpeciality());
            existing.setHospital(doctorDetails.getHospital());
            existing.setStatus(doctorDetails.getStatus());
            return docRepo.save(existing);
        }).orElse(null);
    }

    public boolean deleteDoctor(int id) {
        if (docRepo.existsById(id)) {
            docRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
