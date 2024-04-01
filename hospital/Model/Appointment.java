package com.example.hospital.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Appointment {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID ;


    @NotNull(message = "user id should be not empty")
    @Column(columnDefinition = "int not null")
    private Integer userId ;


    @NotNull(message = "doctor id should be not empty")
    @Column(columnDefinition = "int not null")
    private Integer doctorId ;


    @FutureOrPresent
    private LocalDate date ;


    private Time time ;


    @NotEmpty(message = "reason For Appointment should be not empty")
    @Size(min = 20 , message = "reason For Appointment should be more than 20 char")
    @Column(columnDefinition = "varchar(100) not null")
    private String reasonForAppointment ;


    @Column(columnDefinition = "varchar(25)")
    private String status ;


    @Column(columnDefinition = "boolean")
    private Boolean presence ;

}
