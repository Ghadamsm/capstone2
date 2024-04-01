package com.example.hospital.Repository;

import com.example.hospital.Model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy , Integer> {

    Pharmacy findPharmacyByID(Integer Id);


    Pharmacy findPharmacyByMedicamentNameContainingIgnoreCase(String recipe);


}
