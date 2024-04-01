package com.example.hospital.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Pharmacy {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID ;


    @NotEmpty(message = "medicament Name should be not empty")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String medicamentName ;



    @Positive(message = "stuck should be not empty")
    @Min(value = 10 , message = "stuck should be more than 10 ")
    @Column(columnDefinition = "int not null")
    private Integer stuck ;


}
