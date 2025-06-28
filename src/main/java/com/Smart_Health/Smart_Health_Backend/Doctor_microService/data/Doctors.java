package com.Smart_Health.Smart_Health_Backend.Doctor_microService.data;

import jakarta.persistence.*;

@Entity
@Table(name="doctors")
public class Doctors
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "speciality")
    private String speciality;

    @Column(name = "hospital")
    private String hospital;

    @Column(name = "status")
    private String status;

    //getters
    public Doctors() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getHospital() {
        return hospital;
    }

    public String getStatus() {
        return status;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
