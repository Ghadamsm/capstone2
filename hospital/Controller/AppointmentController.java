package com.example.hospital.Controller;


import com.example.hospital.API.ApiResponse;
import com.example.hospital.Model.Appointment;
import com.example.hospital.Service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointment")
@RequiredArgsConstructor
public class AppointmentController {


    private final AppointmentService appointmentService ;


    @GetMapping("/get")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(appointmentService.getAll());
    }



    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid Appointment appointment , Errors errors){

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
            return ResponseEntity.status(200).body(appointmentService.add(appointment));

    }



    @PutMapping("/update/{Id}")
    public ResponseEntity update(@PathVariable Integer Id , @RequestBody @Valid Appointment appointment, Errors errors){

        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        appointmentService.update(Id , appointment);

        return ResponseEntity.status(200).body(new ApiResponse("Appointment updated"));
    }



    @DeleteMapping("/delete/{Id}")
    public ResponseEntity delete(@PathVariable Integer Id ){

        appointmentService.delete(Id);

        return ResponseEntity.status(200).body(new ApiResponse("Appointment deleted"));
    }



//     endpoint



    @GetMapping("/getD/{sp}")
    public ResponseEntity getAllDoctor(@PathVariable String sp){

        return ResponseEntity.status(200).body( appointmentService.getAllDoctorBySp(sp));
    }



    @GetMapping("/getA/{userId}/{TORF}")
    public ResponseEntity getAllAppoF(@PathVariable Integer userId ,@PathVariable Boolean TORF){

        return ResponseEntity.status(200).body( appointmentService.getAllAppoF(userId, TORF));
    }



    @GetMapping("/visit/{visitsvisit}")
    public ResponseEntity getVisit(@PathVariable Integer visits){

        return ResponseEntity.status(200).body(appointmentService.getVisit(visits));

    }

}
