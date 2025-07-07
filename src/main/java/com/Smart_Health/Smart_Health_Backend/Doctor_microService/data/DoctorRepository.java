// DoctorRepository.java
package com.Smart_Health.Smart_Health_Backend.Doctor_microService.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctors, Integer> {


    List<Doctors> findAllByHospitalIgnoreCase(String hospital);

    List<Doctors> findBySpecialityIgnoreCase(String speciality);

    List<Doctors> findByStatusIgnoreCase(String status);
}
