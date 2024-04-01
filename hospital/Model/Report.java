package com.example.hospital.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID ;


    @NotNull(message = "user id should be not empty")
    @Column(columnDefinition = "int not null")
    private Integer userId ;


    @NotNull(message = "doctor id should be not empty")
    @Column(columnDefinition = "int not null")
    private Integer doctorId ;


    @NotNull(message = "please enter ehr appointment id")
    @Column(columnDefinition = "int not null")
    private Integer appointmentId ;


    @NotEmpty(message = "please enter the report")
    @Column(columnDefinition = "varchar(250) not null")
    private String reports ;


    @Column(columnDefinition = "varchar(60)")
    private String recipe ;


    @Column(columnDefinition = "int")
    private Integer amount ;


    @AssertFalse
    @Column(columnDefinition = "boolean")
    private Boolean refillTheMedication ;


    @DateTimeFormat
    @Column(columnDefinition = "date")
    private LocalDate reportDate ;
}
