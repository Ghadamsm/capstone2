package com.example.hospital.Controller;


import com.example.hospital.API.ApiResponse;
import com.example.hospital.Model.Pharmacy;
import com.example.hospital.Service.PharmacyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pharmacy")
@RequiredArgsConstructor
public class PharmacyController {


    private final PharmacyService pharmacyService ;


    @GetMapping("/get")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(pharmacyService.getAll());
    }



    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid Pharmacy pharmacy , Errors errors){

        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        pharmacyService.add(pharmacy);
        return ResponseEntity.status(200).body(new ApiResponse("Pharmacy added"));

    }



    @PutMapping("/update/{Id}")
    public ResponseEntity update(@PathVariable Integer Id , @RequestBody @Valid Pharmacy pharmacy , Errors errors){

        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        pharmacyService.update(Id , pharmacy);

        return ResponseEntity.status(200).body(new ApiResponse("Pharmacy updated"));
    }



    @DeleteMapping("/delete/{Id}")
    public ResponseEntity delete(@PathVariable Integer Id ){

        pharmacyService.delete(Id);

        return ResponseEntity.status(200).body(new ApiResponse("Pharmacy deleted"));
    }




//      Extra


    @GetMapping("/recipe/{appointmentId}")
    public ResponseEntity recipe( @PathVariable Integer appointmentId ){
        pharmacyService.recipe(appointmentId);

        return ResponseEntity.status(200).body(new ApiResponse("Thank you"));
    }




    @GetMapping("/refillPh/{reportId}")
    public ResponseEntity refillPh(@PathVariable Integer reportId){

        pharmacyService.refillPh(reportId);

        return ResponseEntity.status(200).body(new ApiResponse("thank you"));

    }




    @GetMapping("/restock/{MId}/{amount}")
    public ResponseEntity restock(@PathVariable Integer MId , @PathVariable Integer amount){

        pharmacyService.restock(MId, amount);

        return ResponseEntity.status(200).body(new ApiResponse("restock done"));
    }

}
