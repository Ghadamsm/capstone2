package com.example.hospital.Repository;

import com.example.hospital.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment , Integer> {


    Appointment findAppointmentByID(Integer Id);
    List<Appointment> findAppointmentsByPresence(Boolean TORF);



}
