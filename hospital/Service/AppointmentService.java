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

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {


    private final AppointmentRepository appointmentRepository ;
    private final UserRepository userRepository ;
    private final DoctorRepository doctorRepository ;
    private final DoctorService doctorService ;


    public List<Appointment> getAll(){
        return appointmentRepository.findAll();
    }
    List<String> addApp = new ArrayList<>();




    //  حجز موعد
    public List<String> add(Appointment appointment){
        User u = userRepository.findUserByID(appointment.getUserId());
        Doctor d = doctorRepository.findDoctorByID(appointment.getDoctorId());

        if (u == null || d == null) {
            throw new ApiException("invalid doctor id or user id");
        }

        appointment.setStatus("waiting for your presence");
        appointment.setPresence(Boolean.FALSE);
        appointmentRepository.save(appointment);



        addApp.add(d.getName());
        addApp.add(String.valueOf(appointment.getDate()));
        addApp.add(String.valueOf(appointment.getTime()));
        addApp.add(appointment.getStatus());

        return addApp ;

    }




    public void update(Integer Id , Appointment appointment){

        Appointment a = appointmentRepository.findAppointmentByID(Id);

        if (a == null){
            throw new ApiException("Invalid Id");
        }
        else if (appointment.getDate().isBefore(LocalDate.now().minus(Duration.ofDays(1)))){
            throw new ApiException("you cannot modify the appointment");
        }
            a.setDate(appointment.getDate());
            a.setTime(appointment.getTime());
            a.setDoctorId(appointment.getDoctorId());
            a.setUserId(appointment.getUserId());
            a.setReasonForAppointment(appointment.getReasonForAppointment());
            a.setPresence(Boolean.FALSE);

        appointmentRepository.save(a);

    }




    public void delete(Integer Id){

        Appointment a = appointmentRepository.findAppointmentByID(Id);

        if (a == null){
            throw new ApiException("Invalid Id");
        }

        appointmentRepository.delete(a);

    }





//    endpoint



    //  عرض جميع الدكاترة في تخصص معين
    public List<String> getAllDoctorBySp(String sp){

        List<Doctor> d = doctorRepository.findDoctorsBySpecialty(sp);
        List<String> doc = new ArrayList<>();

        if (d == null){
            throw new ApiException("sorry, there is no doctor in this specialty");
        }

        for (Doctor doctor : d){
             doc.add(doctor.getName());
             doc.add(doctor.getSpecialty());
        }

        return doc ;
    }




    // عرض المواعيد على حسب اذا حضرها تكون ترو و اذا ماحضرها فولس
    public List<Appointment> getAllAppoF(Integer userId ,Boolean TORF){

        User u = userRepository.findUserByID(userId);
        List<Appointment> a = appointmentRepository.findAppointmentsByPresence(TORF);
        if ((u == null) || a == null){
            throw new ApiException("invalid user id or appointment");
        }

        return a ;

    }



    public List<Doctor> getVisit(Integer visits){

        List<Doctor> d = doctorRepository.getDoctorsByVisitGreaterThanEqual(visits);

        if (d == null){
            throw new ApiException("sorry there is no doctor");
        }

        return d ;

    }


}
