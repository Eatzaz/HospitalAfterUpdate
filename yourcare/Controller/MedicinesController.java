package com.example.yourcare.Controller;

import com.example.yourcare.Api.ApiResponse;
import com.example.yourcare.Model.Doctor;
import com.example.yourcare.Model.Medicines;
import com.example.yourcare.Service.MedicinesService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/medicines")
public class MedicinesController {
    private final MedicinesService medicinesService;
    @GetMapping("/get")
    public ResponseEntity getAllMedicines(){
        return ResponseEntity.status(200).body(medicinesService.getAllMedicines());
    }
    @PostMapping("/add")
    public ResponseEntity addMedicines(@RequestBody @Valid Medicines medicines, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        medicinesService.addMedicines(medicines);
        return ResponseEntity.status(200).body(new ApiResponse("Success"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMedicines(@PathVariable Integer id,@RequestBody @Valid Medicines medicines, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isTrue=medicinesService.updateMedicines(id,medicines);
        if(isTrue){
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Valid: The user is Not Exist"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMedicines(@PathVariable Integer id){
        boolean isTrue=medicinesService.deleteMedicines(id);
        if(isTrue){
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Valid: The user is Not Exist"));
    }

}
