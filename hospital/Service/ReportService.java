package com.example.hospital.Service;


import com.example.hospital.API.ApiException;
import com.example.hospital.Model.*;
import com.example.hospital.Repository.AppointmentRepository;
import com.example.hospital.Repository.DoctorRepository;
import com.example.hospital.Repository.ReportRepository;
import com.example.hospital.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {



    private final ReportRepository reportRepository ;
    private final UserRepository userRepository ;
    private final DoctorRepository doctorRepository ;
    private final AppointmentRepository appointmentRepository ;


    public List<Report> getAll(){
        return reportRepository.findAll();
    }


// اضافة تقرير

    public void addReport(Report report){
        User u = userRepository.findUserByID(report.getUserId());
        Doctor d = doctorRepository.findDoctorByID(report.getDoctorId());
        Appointment a = appointmentRepository.findAppointmentByID(report.getAppointmentId());


        if (u == null || d == null || a == null){
            throw new ApiException("invalid doctor id or user id");
        }

        a.setPresence(Boolean.TRUE);
        report.setRefillTheMedication(Boolean.FALSE);
        a.setStatus("Attended");
        report.setReportDate(LocalDate.now());
        d.setVisit(d.getVisit() + 1);


        d.setSalary(d.getSalary() + (d.getSalary() * 3 /100));
        doctorRepository.save(d);


        appointmentRepository.save(a);
        doctorRepository.save(d);
        reportRepository.save(report);


    }




    public void updateReport(Integer Id , Report report){

        Report r = reportRepository.findReportByID(Id);


        if (r == null){
            throw new ApiException("Invalid Id");
        }


        r.setUserId(report.getUserId());
        r.setDoctorId(report.getDoctorId());
        r.setRecipe(report.getRecipe());
        r.setReports(report.getReports());
        r.setReportDate(LocalDate.now());


        reportRepository.save(r);

    }



    public void deleteReport(Integer Id){

        Report r = reportRepository.findReportByID(Id);

        if (r == null){
            throw new ApiException("Invalid Id");
        }

        reportRepository.delete(r);

    }




//    endpoint



    //  تغيير حالة اعادة الصرف الى ترو عشان تنصرف مرة ثانيه في الصيدلية
    public void refill(Integer reportId , Integer amount){
        Report r = reportRepository.findReportByID(reportId);

        if (r == null){
            throw new ApiException("invalid report id");
        }

        if (r.getRefillTheMedication() == false){
            r.setRefillTheMedication(true);
            r.setAmount(amount);
            reportRepository.save(r);
        }


    }











}
