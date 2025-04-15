package com.example.yourcare.Controller;

import com.example.yourcare.Api.ApiResponse;
import com.example.yourcare.Model.PrescriptionMedicines;
import com.example.yourcare.Service.PrescriptionMedicinesService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/Prescription")
public class PrescriptionMedicinesController {
    private final PrescriptionMedicinesService PMS;
    @GetMapping("/get")
    public ResponseEntity getAllPrescriptionMedicines(){
        return ResponseEntity.status(200).body(PMS.getAllPrescriptionMedicines());
    }
    @PostMapping("/add")
    public ResponseEntity addPrescriptionMedicines(@RequestBody @Valid PrescriptionMedicines prescriptionMedicines, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        String isTrue=PMS.addPrescriptionMedicines(prescriptionMedicines);
        if(isTrue.equals("Success")){
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(isTrue);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateReservations(@PathVariable Integer id, @RequestBody @Valid PrescriptionMedicines prescriptionMedicines, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isTrue=PMS.updatePrescriptionMedicines(id,prescriptionMedicines);
        if(isTrue){
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Valid: The prescription Medicines is Not Exist"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteReservations(@PathVariable Integer id){
        boolean isTrue=PMS.DeletePrescriptionMedicines(id);
        if(isTrue){
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Valid: The prescription Medicines  is Not Exist"));
    }
    //9
    @PostMapping("/give")
    public ResponseEntity GiveDescription(@RequestBody @Valid PrescriptionMedicines prescriptionMedicines){
        String isTrue=PMS.GiveDescription(prescriptionMedicines);
        if(isTrue.equals("The prescription has been issued successfully.")){
            return ResponseEntity.status(200).body(new ApiResponse("The prescription has been issued successfully."));
        }
        return ResponseEntity.status(400).body(isTrue);
    }
    //11
    @PostMapping("/check")
    public ResponseEntity check(@RequestBody @Valid PrescriptionMedicines prescriptionMedicines){
        String isTrue=PMS.check(prescriptionMedicines);
        if(isTrue.equals("Success")){
            return ResponseEntity.status(200).body(new ApiResponse("The prescription has been issued successfully."));
        }

        return ResponseEntity.status(400).body(isTrue);
    }

}
