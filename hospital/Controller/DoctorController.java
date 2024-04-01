package com.example.hospital.Controller;


import com.example.hospital.API.ApiResponse;
import com.example.hospital.Model.Doctor;
import com.example.hospital.Service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctor")
@RequiredArgsConstructor
public class DoctorController {


    private final DoctorService doctorService ;



    @GetMapping("/get")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(doctorService.getAll());
    }



    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid Doctor doctor , Errors errors){

        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        doctorService.add(doctor);
        return ResponseEntity.status(200).body(new ApiResponse("Doctor added"));

    }



    @PutMapping("/update/{Id}")
    public ResponseEntity update(@PathVariable Integer Id , @RequestBody @Valid Doctor doctor, Errors errors){

        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        doctorService.update(Id , doctor);

        return ResponseEntity.status(200).body(new ApiResponse("Doctor updated"));
    }



    @DeleteMapping("/delete/{Id}")
    public ResponseEntity delete(@PathVariable Integer Id ){

        doctorService.delete(Id);

        return ResponseEntity.status(200).body(new ApiResponse("Doctor deleted"));
    }





//     endpoint




    @GetMapping("/bonus/{DID}/{bonus}")
    public ResponseEntity bonuses(@PathVariable Integer DID , @PathVariable Integer bonus){

        doctorService.bonuses(DID, bonus);

        return ResponseEntity.status(200).body(new ApiResponse("bonus added"));
    }




    @GetMapping("/searchByNC/{nationality}/{certification}")
    public ResponseEntity getDoctorByNationalityAndCertification(@PathVariable String nationality ,@PathVariable String certification){

        return ResponseEntity.status(200).body(doctorService.getDoctorByNationalityAndCertification(nationality, certification));
    }
}
