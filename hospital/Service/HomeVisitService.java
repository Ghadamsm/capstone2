package com.example.hospital.Service;


import com.example.hospital.API.ApiException;
import com.example.hospital.Model.Doctor;
import com.example.hospital.Model.HomeVisit;
import com.example.hospital.Model.User;
import com.example.hospital.Repository.DoctorRepository;
import com.example.hospital.Repository.HomeVisitRepository;
import com.example.hospital.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeVisitService {



    private final HomeVisitRepository homeVisitRepository ;
    private final DoctorRepository doctorRepository ;
    private final UserRepository userRepository ;



    List<String> addHomeVisit = new ArrayList<>();



    public List<HomeVisit> getAll(){
        return homeVisitRepository.findAll();
    }



//    home visit اضافة موعد

    public List<String> add(HomeVisit homeVisit){
        User u = userRepository.findUserByID(homeVisit.getUserId());
        Doctor d = doctorRepository.findDoctorByID(homeVisit.getDoctorId());

        if (u == null || d == null){
                throw new ApiException("invalid doctor id or user id");
        }

        homeVisit.setStatusForUser("wait for us to arrive on time");
        homeVisit.setStatus(Boolean.FALSE);
        homeVisitRepository.save(homeVisit);


        d.setVisit(d.getVisit() + 1);
        d.setSalary(d.getSalary() + 400);
        doctorRepository.save(d);


        addHomeVisit.add(d.getName());
        addHomeVisit.add(String.valueOf(homeVisit.getDate()));
        addHomeVisit.add(String.valueOf(homeVisit.getTime()));
        addHomeVisit.add(homeVisit.getStatusForUser());

        return addHomeVisit ;
    }



    public void update(Integer Id , HomeVisit homeVisit){

        HomeVisit h = homeVisitRepository.findHomeVisitByID(Id);

        if (h == null){
            throw new ApiException("Invalid Id");
        }


        h.setReason(homeVisit.getReason());
        h.setDoctorId(homeVisit.getDoctorId());
        h.setUserId(homeVisit.getUserId());

        homeVisitRepository.save(h);

    }



    public void delete(Integer Id){

        HomeVisit h = homeVisitRepository.findHomeVisitByID(Id);

        if (h == null){
            throw new ApiException("Invalid Id");
        }

        homeVisitRepository.delete(h);

    }



//    endpoint



    //   تغيير حالة الزيارة الى تمت
    public void status(Integer Id){
        HomeVisit h = homeVisitRepository.findHomeVisitByID(Id);
        Doctor d = doctorRepository.findDoctorByID(h.getDoctorId());
        if (h == null){
            throw new ApiException("Invalid id");
        }

        d.setVisit(d.getVisit() + 1);
        doctorRepository.save(d);

        h.setStatus(Boolean.TRUE);
        h.setStatusForUser("thank you");

        homeVisitRepository.save(h);
    }



    // عرض جميع الدكاترة لتخصص معين


    public List<String> getAllDoctorBySp(String specialty){
        List<Doctor> doctors = doctorRepository.findDoctorsBySpecialty(specialty);
        List<String> doc = new ArrayList<>();

        if (doctors.isEmpty()){
            throw new ApiException("sorry, there is no doctor in this specialty");
        }

        for (Doctor doctor : doctors){
            doc.add(doctor.getName());
            doc.add(doctor.getSpecialty());
        }

        return doc ;
    }




    //  عرض الدكتور الاكثر مواعيد خلال الشهر


    public List<Doctor> bestDoctor(Integer visit){

        List<Doctor> d = doctorRepository.getDoctorsByVisitGreaterThanEqual(visit);


        if (d == null){
            throw new ApiException("sorry there is no doctor");
        }

        return d ;
    }





}
