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

public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID ;


    @NotEmpty(message = "name should be not empty")
    @Size(min = 4 , message = "name should be more than 4 char")
    @Column(columnDefinition = "varchar(30) not null")
    private String name ;


    @NotEmpty(message = "password should be not empty")
    @Size(min = 5 , message = "password should be more than 4 char")
    @Column(columnDefinition = "varchar(15) unique not null")
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



    @NotEmpty(message = "please enter your location")
    @Column(columnDefinition = "varchar(50) not null ")
    private String userLocation ;

}
