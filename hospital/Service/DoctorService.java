package com.example.hospital.Service;


import com.example.hospital.API.ApiException;
import com.example.hospital.Model.Appointment;
import com.example.hospital.Model.Doctor;
import com.example.hospital.Model.User;
import com.example.hospital.Repository.AppointmentRepository;
import com.example.hospital.Repository.DoctorRepository;
import com.example.hospital.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {



    private final DoctorRepository doctorRepository ;


    List<Integer> doctors = new ArrayList<>();


    public List<Doctor> getAll(){
        return doctorRepository.findAll();
    }


    public void add(Doctor doctor){

        doctorRepository.save(doctor);
    }



    public void update(Integer Id , Doctor doctor){

        Doctor d = doctorRepository.findDoctorByID(Id);

        if (d == null){
            throw new ApiException("Invalid Id");
        }


        d.setName(doctor.getName());
        d.setAge(doctor.getAge());
        d.setEmail(doctor.getEmail());
        d.setPassword(doctor.getPassword());
        d.setPhoneNumber(doctor.getPhoneNumber());
        d.setSalary(doctor.getSalary());
        d.setSpecialty(doctor.getSpecialty());


        doctorRepository.save(d);

    }



    public void delete(Integer Id){

        Doctor d = doctorRepository.findDoctorByID(Id);

        if (d == null){
            throw new ApiException("Invalid Id");
        }

        doctorRepository.delete(d);

    }



//    endpoint


// بونس على الراتب

    public void bonuses(Integer DoID , Integer bonus){
        Doctor d = doctorRepository.findDoctorByID(DoID);
        if (d == null){
            throw new ApiException("invalid id");
        }

        Integer doctor = d.getSalary() + (d.getSalary() * bonus / 100);
        d.setSalary(doctor);

        doctorRepository.save(d);
    }








// عرض الدكتور عن طريق الجنسية و الشهادة

    public List<String> getDoctorByNationalityAndCertification(String nationality , String certification){

        List<Doctor> doctors = doctorRepository.findDoctorsByNationalityAndCertification(nationality, certification);
        List<String> doctor = new ArrayList<>();

        if (doctors.isEmpty()){
           throw new ApiException("sorry , there is no doctor that matches your search");
        }

        for (Doctor d : doctors){
            doctor.add(d.getName());
            doctor.add(d.getCertification());
            doctor.add(d.getNationality());
        }

        return doctor ;
    }


}
