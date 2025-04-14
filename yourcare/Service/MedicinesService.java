package com.example.yourcare.Service;

import com.example.yourcare.Model.Doctor;
import com.example.yourcare.Model.Medicines;
import com.example.yourcare.Repository.MedicinesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MedicinesService {
    private final MedicinesRepository medicinesRepository;
    public List<Medicines> getAllMedicines(){
       List<Medicines> medicines=medicinesRepository.findAll();
        return medicines;
    }
    public void addMedicines(Medicines medicines){
       medicinesRepository.save(medicines);
    }
    public boolean updateMedicines(Integer id,Medicines medicines){
       Medicines medicines1=medicinesRepository.findMedicinesByIdMedicines(id);
        if(medicines1==null){
            return false;
        }
       medicines1.setName(medicines.getName());
        medicines1.setTypeOfDisease(medicines.getTypeOfDisease());
        medicinesRepository.save(medicines1);
        return true;
    }
    public boolean deleteMedicines(Integer id){
        Medicines medicines=medicinesRepository.findMedicinesByIdMedicines(id);
        if(medicines==null){
            return false;
        }
        medicinesRepository.delete(medicines);
        return true;
    }
}
