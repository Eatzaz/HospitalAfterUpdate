package com.example.yourcare.Controller;

import com.example.yourcare.Api.ApiResponse;
import com.example.yourcare.Model.Services;
import com.example.yourcare.Model.User;
import com.example.yourcare.Service.ServiceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/service")
public class ServiceController {
    private final ServiceService serviceService;

    @GetMapping("/get")
    public ResponseEntity getAllService() {
        return ResponseEntity.status(200).body(serviceService.getAllService());
    }

    @PostMapping("/add")
    public ResponseEntity addService(@RequestBody @Valid Services services, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        serviceService.addService(services);
        return ResponseEntity.status(200).body(new ApiResponse("Success"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateService(@PathVariable Integer id, @RequestBody @Valid Services services, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isTrue = serviceService.updateService(id, services);
        if (isTrue) {
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Valid: The user is Not Exist"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteService(@PathVariable Integer id) {
        boolean isTrue = serviceService.deleteService(id);
        if (isTrue) {
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Valid: The user is Not Exist"));
    }

    //2
    @PostMapping("/order")
    public ResponseEntity OrderChair(@RequestBody @Valid Services services, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        String isTrue = serviceService.OrderChair(services);
        if (isTrue.equals("The chair has been successfully ordered.")) {
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(isTrue);
    }
//3
    @PostMapping("/chairOrder")
    public ResponseEntity OrderChairE(@RequestBody @Valid Services services,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        String isTrue=serviceService.OrderChairElderly(services);
        if(isTrue.equals("The chair has been successfully ordered.")){
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(isTrue);
    }
    //4
    @PostMapping("selectService")
    public ResponseEntity selectService(@RequestBody @Valid Services services,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        String isTrue=serviceService.selectService(services);
        if(isTrue.equals("Success")){
            return ResponseEntity.status(200).body(new ApiResponse("Success"));
        }
        return ResponseEntity.status(400).body(isTrue);
    }
    //5
    @PostMapping("orderNurse")
    public ResponseEntity orderNurse(@RequestBody @Valid Services services,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        String isTrue=serviceService.RequestNurse(services);
        if(isTrue.equals("The nurse has been successfully requested.")){
            return ResponseEntity.status(200).body(new ApiResponse("The nurse has been successfully requested."));
        }
        return ResponseEntity.status(400).body(isTrue);
    }
    //6
    @PostMapping("orderAmb")
    public ResponseEntity orderAmboulance(@RequestBody @Valid Services services,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        String isTrue=serviceService.RequsetAmbulans(services);
        if(isTrue.equals("The Ambulance has been successfully requested.")){
            return ResponseEntity.status(200).body(new ApiResponse("The Ambulance has been successfully requested."));
        }
        return ResponseEntity.status(400).body(isTrue);
    }
    //8
    @PostMapping("gift")
    public ResponseEntity giftChair(@RequestBody @Valid Services services,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        String isTrue=serviceService.giftChair(services);
        if(isTrue.equals("A chair was sent as a gift.")){
            return ResponseEntity.status(200).body(new ApiResponse("A chair was sent as a gift."));
        }
        return ResponseEntity.status(400).body(isTrue);
    }
    //12:
    @PutMapping("/ReturnAm/{serviceId}")
    public ResponseEntity ReturnAmb(@PathVariable Integer serviceId){
        String isTrue=serviceService.ReturnAmb(serviceId);
        if(isTrue.equals("Success")){
            return ResponseEntity.status(200).body(new ApiResponse("Sucsses return"));
        }
        return ResponseEntity.status(400).body(isTrue);
    }
    @PutMapping("/Return/{serviceId}")
    public ResponseEntity ReturnNurs(@PathVariable Integer serviceId){
        String isTrue=serviceService.ReturnNurse(serviceId);
        if(isTrue.equals("Success")){
            return ResponseEntity.status(200).body(new ApiResponse("Sucsses return"));
        }
        return ResponseEntity.status(400).body(isTrue);
    }
    @GetMapping("/tools/{tool}")
    public ResponseEntity getToolse(@PathVariable String tool){
        List<Services> isTrue=serviceService.getServiceBy(tool);
        if(isTrue==null){
            return ResponseEntity.status(400).body(new ApiResponse("This tool has not been requested"));
        }
        return ResponseEntity.status(400).body(isTrue);
    }
    }

