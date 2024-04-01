package com.example.hospital.Repository;

import com.example.hospital.Model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report , Integer> {


    Report findReportByID(Integer Id);



}
