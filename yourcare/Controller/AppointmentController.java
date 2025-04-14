package com.example.yourcare.Controller;

import com.example.yourcare.Api.ApiResponse;
import com.example.yourcare.Model.Appointment;
import com.example.yourcare.Service.AppointmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/reservation")
public class AppointmentController {
    private final AppointmentService appointmentService;
    @GetMapping("/get")
    public ResponseEntity getAllReservations(){
        return ResponseEntity.status(200).body(appointmentService.getAllReservation());
    }
    @PostMapping("/add")
    public ResponseEntity addReservations(@RequestBody @Valid Appointment appointment, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
       String isTrue= appointmentService.addReservation(appointment);
        if(isTrue.equals("true")){
        return ResponseEntity.status(200).body(new ApiResponse("Success"));}
        return ResponseEntity.status(400).body(isTrue);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateReservations(@PathVariable Integer id, @RequestBody @Valid Appointment appointment, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isTrue= appointmentService.updateReservations(id, appointment);
        if(isTrue){
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Valid: The user is Not Exist"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteReservations(@PathVariable Integer id){
        boolean isTrue= appointmentService.deleteReservations(id);
        if(isTrue){
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Valid: The user is Not Exist"));
    }
//1
    @PostMapping("reservations")
    public ResponseEntity reservations(@RequestBody @Valid Appointment appointment, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        String isTrue= appointmentService.reservations(appointment);
        if(isTrue.equals("Success")){
            return ResponseEntity.status(200).body(new ApiResponse("An appointment has been booked with the doctor."));
        }
        return ResponseEntity.status(400).body(isTrue);
    }
    //10
    @PostMapping("/online")
    public ResponseEntity OnlineEn(@RequestBody @Valid Appointment appointment){
        String isTrue= appointmentService.EmergencyReservation(appointment);
        if(isTrue.equals("The doctor is currently unavailable, an appointment has been made with the emergency doctor.")){
            return ResponseEntity.status(200).body(new ApiResponse("The doctor is currently unavailable, an appointment has been made with the emergency doctor."));
        } if(isTrue.equals("An appointment has been booked with the doctor.")){
            return ResponseEntity.status(200).body(new ApiResponse("An appointment has been booked with the doctor."));
        }
        return ResponseEntity.status(400).body(isTrue);
    }



}
