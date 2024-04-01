package com.example.hospital.Repository;

import com.example.hospital.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor , Integer> {


    Doctor findDoctorByID(Integer Id);


    List<Doctor> findDoctorsBySpecialty(String sp);


    List<Doctor> findDoctorsByNationalityAndCertification(String nationality , String certification);


    List<Doctor> getDoctorsByVisitGreaterThanEqual(Integer visits);

}
