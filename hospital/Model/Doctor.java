package com.example.hospital.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Doctor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID ;


    @NotEmpty(message = "name should be not empty")
    @Size(min = 4 , message = "name should be more than 4 char")
    @Column(columnDefinition = "varchar(30) not null")
    private String name ;


    @NotEmpty(message = "password should be not empty")
    @Size(min = 5 , message = "password should be more than 4 char")
    @Column(columnDefinition = "varchar(15) not null unique")
    private String password ;



    @NotEmpty(message = "phone number should be not empty")
    @Size(min = 10 , message = "phone number should be 10 ")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String phoneNumber ;


    @Email
    @NotEmpty(message = "email should be not empty")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String email ;



    @NotNull(message = "age should be not empty")
    @Column(columnDefinition = "int not null")
    private Integer age ;



    @NotEmpty(message = "specialty should be not empty")
    @Pattern(regexp = "^(cardiologist|internal|nephrology|oncologist|surgeon|pediatrician|ENT|dentist)$"
            , message = "specialty must be cardiologist ,internal ,nephrology ,oncologist ,surgeon ,pediatrician ,ENT ,dentist ")
    @Column(columnDefinition = "varchar(14) not null check(specialty = 'cardiologist' or specialty = 'internal' or specialty = 'nephrology' or specialty = " +
            "'oncologist' or specialty = 'surgeon' or specialty = 'pediatrician' or specialty = 'ENT' or specialty = 'dentist' )")
    private String specialty ;



    @NotNull(message = "salary should be not empty")
    @Positive(message = "positive number only")
    @Column(columnDefinition = "int not null")
    private Integer salary ;



    @NotEmpty(message = "please enter your nationality")
    @Column(columnDefinition = "varchar(50) not null")
    private String nationality ;



    @NotNull(message = "please enter your years of experience")
    @Column(columnDefinition = "int")
    private Integer yearsOfExperience ;


    @NotEmpty(message = "please enter your certification")
    @Column(columnDefinition = "varchar(250) not null")
    private String certification ;



    @Column(columnDefinition = "int")
    private Integer visit = 0;


}
