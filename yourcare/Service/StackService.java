package com.example.yourcare.Service;

import com.example.yourcare.Model.Stack;
import com.example.yourcare.Repository.StackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StackService {
    private final StackRepository stackRepository;
    public List<Stack> getAllService(){
        return stackRepository.findAll();
    }
    public void addService(Stack serviceStore){
        stackRepository.save(serviceStore);
    }
    public boolean updateService(Integer id, Stack serviceStore){
        Stack services1= stackRepository.findServiceStoreById(id);
        if(services1==null){
            return false;
        }
        services1.setNumberNurse(serviceStore.getNumberNurse());
        services1.setNumberAmbulance(serviceStore.getNumberAmbulance());
        stackRepository.save(services1);
        return true;
    }
    public boolean deleteService(Integer id){
        Stack servicesStore= stackRepository.findServiceStoreById(id);
        if(servicesStore==null){
            return false;
        }
        stackRepository.delete(servicesStore);
        return true;
    }

}
