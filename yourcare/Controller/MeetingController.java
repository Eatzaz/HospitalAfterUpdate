package com.example.yourcare.Controller;

import com.example.yourcare.Api.ApiResponse;
import com.example.yourcare.Model.Meeting;
import com.example.yourcare.Service.MeetingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/meeting")
public class MeetingController {
    private final MeetingService meetingService;
    @GetMapping("/get")
    public ResponseEntity getAllMeeting(){
        return ResponseEntity.status(200).body(meetingService.getAllMeeting());
    }
    @PostMapping("/add")
    public ResponseEntity addMeeting(@RequestBody @Valid Meeting meeting, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        String isTrue=meetingService.addMeeting(meeting);
        if(isTrue.equals("Success")){
            return ResponseEntity.status(200).body(new ApiResponse("Success"));}
        return ResponseEntity.status(400).body(isTrue);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMeeting(@PathVariable Integer id, @RequestBody @Valid Meeting meeting, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isTrue=meetingService.updateMeeting(id,meeting);
        if(isTrue){
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Valid: The meeting is Not Exist"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMeeting(@PathVariable Integer id){
        boolean isTrue=meetingService.deleteMeeting(id);
        if(isTrue){
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Valid: The meeting is Not Exist"));
    }
}
