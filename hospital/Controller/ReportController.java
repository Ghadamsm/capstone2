package com.example.hospital.Controller;


import com.example.hospital.API.ApiResponse;
import com.example.hospital.Model.Report;
import com.example.hospital.Service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/report")
@RequiredArgsConstructor
public class ReportController {



    private final ReportService reportService ;



    @GetMapping("/get")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(reportService.getAll());
    }



    @PostMapping("/add")
    public ResponseEntity addReport(@RequestBody @Valid Report report , Errors errors){

        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        reportService.addReport(report);
        return ResponseEntity.status(200).body(new ApiResponse("Report added"));

    }



    @PutMapping("/update/{Id}")
    public ResponseEntity updateReport(@PathVariable Integer Id , @RequestBody @Valid Report report , Errors errors){

        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        reportService.updateReport(Id , report);

        return ResponseEntity.status(200).body(new ApiResponse("Report updated"));
    }



    @DeleteMapping("/delete/{Id}")
    public ResponseEntity deleteReport(@PathVariable Integer Id ){

        reportService.deleteReport(Id);

        return ResponseEntity.status(200).body(new ApiResponse("Report deleted"));
    }




//    endpoint


    @GetMapping("/refill/{reportId}/{amount}")
    public ResponseEntity refill(@PathVariable Integer reportId , @PathVariable Integer amount){

        reportService.refill(reportId , amount);

        return ResponseEntity.status(200).body(new ApiResponse("go to pharmacy"));
    }

}
