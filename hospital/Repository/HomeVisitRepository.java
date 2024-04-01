package com.example.hospital.Repository;

import com.example.hospital.Model.HomeVisit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public interface HomeVisitRepository extends JpaRepository<HomeVisit , Integer> {


    HomeVisit findHomeVisitByID(Integer Id);


}
