package com.example.yourcare.Controller;

import com.example.yourcare.Api.ApiResponse;
import com.example.yourcare.Model.Admin;
import com.example.yourcare.Model.Doctor;
import com.example.yourcare.Service.AdminService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/a[i/v1/admin")
public class AdminController {
    private final AdminService adminService;
    @GetMapping("/get")
    public ResponseEntity getAdmin(){
        return ResponseEntity.status(200).body(adminService.getAdmin());
    }
    @PostMapping("/add")
    public ResponseEntity addAdmin(@RequestBody @Valid Admin admin, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
adminService.addAdmin(admin);
        return ResponseEntity.status(200).body(new ApiResponse("Success"));

    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateAdmin(@PathVariable Integer id,@RequestBody @Valid Admin admin, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isTrue=adminService.updateAdmin(id,admin);
        if(isTrue){
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Valid: The Admin is Not Exist"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAdmin(@PathVariable Integer id){
        boolean isTrue=adminService.deleteAdmin(id);
        if(isTrue){
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Valid: The Admin is Not Exist"));
    }
}
