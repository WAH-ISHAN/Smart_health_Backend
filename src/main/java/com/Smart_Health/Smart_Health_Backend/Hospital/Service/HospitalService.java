package com.Smart_Health.Smart_Health_Backend.Hospital.Service;

import com.Smart_Health.Smart_Health_Backend.Hospital.Entity.Hospital;
import com.Smart_Health.Smart_Health_Backend.Hospital.Repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository repository;

    public List<Hospital> getAllHospitals() {
        return repository.findAll();
    }

    public Optional<Hospital> getHospitalById(Long id) {
        return repository.findById(id);
    }

    public Hospital addHospital(Hospital hospital) {
        return repository.save(hospital);
    }

    public Optional<Hospital> updateHospital(Long id, Hospital updated) {
        return repository.findById(id).map(h -> {
            if (updated.getName() != null) h.setName(updated.getName());
            if (updated.getAddress() != null) h.setAddress(updated.getAddress());
            if (updated.getLocation() != null) h.setLocation(updated.getLocation());
            return repository.save(h);
        });
    }

    public void deleteHospital(Long id) {
        repository.deleteById(id);
    }

    // New method: search hospitals by name or location ignoring case
    public List<Hospital> searchHospitals(String search) {
        String keyword = search.toLowerCase();
        return repository.searchHospitals(keyword);
    }
}
