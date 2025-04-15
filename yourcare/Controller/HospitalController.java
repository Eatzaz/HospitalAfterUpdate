package com.example.yourcare.Controller;

import com.example.yourcare.Api.ApiResponse;
import com.example.yourcare.Model.Doctor;
import com.example.yourcare.Model.Hospital;
import com.example.yourcare.Repository.HospitalRepository;
import com.example.yourcare.Service.HospitalService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/hospital")
public class HospitalController {
    private final HospitalService hospitalService;
    @GetMapping("/get")
    public ResponseEntity getHospital(){
        return ResponseEntity.status(200).body(hospitalService.getHospital());
    }
    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody @Valid Hospital hospital, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
String isTrue=hospitalService.addHospital(hospital);
        if(isTrue.equals("Success")){
    return ResponseEntity.status(200).body(new ApiResponse("Success"));}
        return ResponseEntity.status(400).body(isTrue);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateHospital(@PathVariable Integer id,@RequestBody @Valid Hospital hospital, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isTrue=hospitalService.updateHospital(id,hospital);
        if(isTrue){
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Valid: The Hospital is Not Exist"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteHospital(@PathVariable Integer id){
        boolean isTrue=hospitalService.deleteHospital(id);
        if(isTrue){
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Valid: The Hospital is Not Exist"));
    }
}
