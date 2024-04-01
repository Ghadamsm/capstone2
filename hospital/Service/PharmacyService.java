package com.example.hospital.Service;


import com.example.hospital.API.ApiException;
import com.example.hospital.Model.Pharmacy;
import com.example.hospital.Model.Report;
import com.example.hospital.Repository.PharmacyRepository;
import com.example.hospital.Repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PharmacyService {

    private final PharmacyRepository pharmacyRepository ;
    private final ReportRepository reportRepository ;
    private final ReportService reportService ;


    public List<Pharmacy> getAll(){
        return pharmacyRepository.findAll();
    }


    public void add(Pharmacy pharmacy){

        pharmacyRepository.save(pharmacy);
    }



    public void update(Integer Id , Pharmacy pharmacy){

        Pharmacy p = pharmacyRepository.findPharmacyByID(Id);

        if (p == null){
            throw new ApiException("Invalid Id");
        }


        p.setStuck(pharmacy.getStuck());
        p.setMedicamentName(pharmacy.getMedicamentName());


        pharmacyRepository.save(p);

    }



    public void delete(Integer Id){

        Pharmacy p = pharmacyRepository.findPharmacyByID(Id);

        if (p == null){
            throw new ApiException("Invalid Id");
        }

        pharmacyRepository.delete(p);

    }





//      endPoint


    //  صرف الدواء
    public void recipe(Integer appointmentId){
        Report reportId = reportRepository.findReportByID(appointmentId);
        if (reportId == null){
            throw new ApiException("Invalid appointment");
        }

        Pharmacy isRecipe = pharmacyRepository.findPharmacyByMedicamentNameContainingIgnoreCase(reportId.getRecipe());
        if (isRecipe == null || isRecipe.getStuck() ==0){
            throw new ApiException("sorry we don't have this medicament");
        }

        Integer amo = isRecipe.getStuck() - reportId.getAmount() ;
        isRecipe.setStuck(amo);

        pharmacyRepository.save(isRecipe);

    }




    //  اعادة صرف الدواء بالعدد المعين
    public void refillPh(Integer reportId){
        Report re = reportRepository.findReportByID(reportId);

        if (re == null){
            throw new ApiException("invalid report id");
        }
        if (re.getRefillTheMedication().equals(true)) {
            Pharmacy recipe = pharmacyRepository.findPharmacyByMedicamentNameContainingIgnoreCase(re.getRecipe());
                if (recipe == null){
                     throw new ApiException("sorry we don't have this medicament");
                }
            recipe.setStuck(recipe.getStuck() - re.getAmount());
                pharmacyRepository.save(recipe);
        }

    }




    //  اعادة ملء المخزن بدواء معين بعدد معين
    public void restock(Integer MId , Integer amount){
        Pharmacy p = pharmacyRepository.findPharmacyByID(MId);
        if (p == null){
            throw new ApiException("invalid id");
        }

        p.setStuck(p.getStuck() + amount);

        pharmacyRepository.save(p);

    }




}
