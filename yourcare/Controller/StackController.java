package com.example.yourcare.Controller;

import com.example.yourcare.Api.ApiResponse;
import com.example.yourcare.Model.Stack;
import com.example.yourcare.Service.StackService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/stack")
public class StackController {
    private final StackService stackService;
    @GetMapping("/get")
    public ResponseEntity getAllService(){
        return ResponseEntity.status(200).body(stackService.getAllService());
    }
    @PostMapping("/add")
    public ResponseEntity addService(@RequestBody @Valid Stack serviceStore, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        stackService.addService(serviceStore);
    return ResponseEntity.status(200).body(new ApiResponse("Success"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateDoctor(@PathVariable Integer id, @RequestBody @Valid Stack serviceStore, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isTrue=stackService.updateService(id,serviceStore);
        if(isTrue){
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Valid: The Service place is Not Exist"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDoctor(@PathVariable Integer id){
        boolean isTrue=stackService.deleteService(id);
        if(isTrue){
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Valid: The Service place is Not Exist"));
    }
}
