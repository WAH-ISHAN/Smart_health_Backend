package com.Smart_Health.Smart_Health_Backend.Hospital.Controller;

import com.Smart_Health.Smart_Health_Backend.Hospital.Entity.Hospital;
import com.Smart_Health.Smart_Health_Backend.Hospital.Service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
@CrossOrigin(origins = "http://localhost:5173")
public class HospitalController {

    @Autowired
    private HospitalService service;

    @GetMapping("/search")
    public List<Hospital> getAll(@RequestParam(value = "search", required = false) String search) {
        if (search == null || search.isEmpty()) {
            return service.getAllHospitals();
        } else {

            return service.searchHospitals(search.toLowerCase());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospital> getById(@PathVariable Long id) {
        return service.getHospitalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Hospital> create(@RequestBody Hospital hospital) {
        Hospital saved = service.addHospital(hospital);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hospital> update(@PathVariable Long id, @RequestBody Hospital hospital) {
        return service.updateHospital(id, hospital)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteHospital(id);
        return ResponseEntity.noContent().build();
    }
}
