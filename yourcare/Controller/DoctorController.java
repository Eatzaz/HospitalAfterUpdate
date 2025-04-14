package com.example.yourcare.Controller;

import com.example.yourcare.Api.ApiResponse;
import com.example.yourcare.Model.Doctor;
import com.example.yourcare.Service.DoctorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/doctor")
public class DoctorController {
    private final DoctorService doctorService;
    @GetMapping("/get")
    public ResponseEntity getAllDoctor(){
        return ResponseEntity.status(200).body(doctorService.getAlldoctor());
    }
    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody @Valid Doctor doctor, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        doctorService.addDoctor(doctor);
        return ResponseEntity.status(200).body(new ApiResponse("Success"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateDoctor(@PathVariable Integer id,@RequestBody @Valid Doctor doctor, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isTrue=doctorService.updateDoctor(id,doctor);
        if(isTrue){
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Valid: The Doctor is Not Exist"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDoctor(@PathVariable Integer id){
        boolean isTrue=doctorService.deleteDoctor(id);
        if(isTrue){
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Valid: The Doctor is Not Exist"));
    }
}
