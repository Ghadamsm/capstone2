package com.example.hospital.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class HomeVisit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID ;


    @NotNull(message = "user id should be not empty")
    @Column(columnDefinition = "int not null")
    private Integer userId ;


    @NotNull(message = "doctor id should be not empty")
    @Column(columnDefinition = "int not null")
    private Integer doctorId ;


    @Column(columnDefinition = "boolean")
    private Boolean status ;


    @Column(columnDefinition = "varchar(60)")
    private String statusForUser ;


    @NotEmpty(message = "the reason should be not empty")
    @Size(min = 20 , message = "the reason should be more than 30 char")
    @Column(columnDefinition = "varchar(100) not null")
    private String reason ;


    @FutureOrPresent
    private LocalDate date ;


    private Time time ;

}
