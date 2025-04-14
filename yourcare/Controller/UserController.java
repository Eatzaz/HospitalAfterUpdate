package com.example.yourcare.Controller;

import com.example.yourcare.Api.ApiResponse;
import com.example.yourcare.Model.User;
import com.example.yourcare.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/get")
    public ResponseEntity getAllUser(){
        return ResponseEntity.status(200).body(userService.getAllUser());
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("Success"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id,@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isTrue=userService.updateUser(id,user);
        if(isTrue){
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Valid: The user is Not Exist"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        boolean isTrue=userService.deleteUser(id);
        if(isTrue){
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Valid: The user is Not Exist"));
    }
//7
    @GetMapping("status/{userId}")
    public ResponseEntity WhatStatus(@PathVariable Integer userId){
        String isTrue=userService.WhatStatus(userId);
        if(isTrue.equals("The user is elderly and has special needs.")){
            return ResponseEntity.status(200).body(new ApiResponse("The user is elderly and has special needs."));
        }
        return ResponseEntity.status(400).body(isTrue);
    }
    //14
    @GetMapping("age/{des}/{age}")
    public ResponseEntity getDisease(@PathVariable String des,@PathVariable Integer age){
        List<User> isTrue=userService.getDisease(des,age);
        if(isTrue==null){
            return ResponseEntity.status(400).body(new ApiResponse("Not Exist"));
        }
        return ResponseEntity.status(200).body(isTrue);
    }
}
