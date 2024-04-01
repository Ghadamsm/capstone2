package com.example.hospital.Controller;


import com.example.hospital.API.ApiResponse;
import com.example.hospital.Model.HomeVisit;
import com.example.hospital.Service.HomeVisitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/home")
@RequiredArgsConstructor
public class HomeVisitController {



    private final HomeVisitService homeVisitService ;


    @GetMapping("/get")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(homeVisitService.getAll());
    }



    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid HomeVisit homeVisit , Errors errors){

        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }


        return ResponseEntity.status(200).body(homeVisitService.add(homeVisit));

    }



    @PutMapping("/update/{Id}")
    public ResponseEntity update(@PathVariable Integer Id , @RequestBody @Valid HomeVisit homeVisit , Errors errors){

        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        homeVisitService.update(Id , homeVisit);

        return ResponseEntity.status(200).body(new ApiResponse("HomeVisit updated"));
    }



    @DeleteMapping("/delete/{Id}")
    public ResponseEntity delete(@PathVariable Integer Id ){

        homeVisitService.delete(Id);

        return ResponseEntity.status(200).body(new ApiResponse("HomeVisit deleted"));
    }




//     endpoint




    @GetMapping("/status/{Id}")
    public ResponseEntity status(@PathVariable Integer Id){

        homeVisitService.status(Id);

        return ResponseEntity.status(200).body(new ApiResponse("Thank you for requesting the home visit service"));
    }



    @GetMapping("/getD/{specialty}")
    public ResponseEntity getAllDoctor(@PathVariable String specialty){

        return ResponseEntity.status(200).body( homeVisitService.getAllDoctorBySp(specialty));
    }




    @GetMapping("/getBest/{visit}")
    public ResponseEntity bestDoctor(@PathVariable Integer visit ){

        return ResponseEntity.status(200).body(homeVisitService.bestDoctor(visit));
    }



}
