package com.example.yourcare.Service;

import com.example.yourcare.Model.Doctor;
import com.example.yourcare.Model.Medicines;
import com.example.yourcare.Model.PrescriptionMedicines;
import com.example.yourcare.Model.User;
import com.example.yourcare.Repository.DoctorRepository;
import com.example.yourcare.Repository.MedicinesRepository;
import com.example.yourcare.Repository.PrescriptionMedicinesRepository;
import com.example.yourcare.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
@AllArgsConstructor
public class PrescriptionMedicinesService {
    private final PrescriptionMedicinesRepository PMR;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final MedicinesRepository medicinesRepository;
    public List<PrescriptionMedicines>getAllPrescriptionMedicines(){
        return PMR.findAll();
    }
    public String addPrescriptionMedicines(PrescriptionMedicines prescriptionMedicines){
        User user=userRepository.findUserByIdUser(prescriptionMedicines.getIdUser());
        Doctor doctor=doctorRepository.findDoctorByIdDoctor(prescriptionMedicines.getIdDoctor());
        Medicines medicines=medicinesRepository.findMedicinesByIdMedicines(prescriptionMedicines.getIdMedicines());
        if(user==null){
            return "User Not Found";
        }
        if(doctor==null){
            return "Doctor Not Found";
        }
        if(medicines==null){
            return "medicines Not Found";
        }
        PMR.save(prescriptionMedicines);
        return "Success";
    }
    public boolean updatePrescriptionMedicines(Integer id,PrescriptionMedicines prescriptionMedicines){
        PrescriptionMedicines pm=PMR.findPrescriptionMedicicnesByIdPrescription(id);
        if(pm==null){
            return false;
        }
        pm.setIdMedicines(prescriptionMedicines.getIdMedicines());
        pm.setIdDoctor(prescriptionMedicines.getIdDoctor());
        pm.setIdUser(prescriptionMedicines.getIdUser());
        PMR.save(pm);
        return true;
    }
    public boolean DeletePrescriptionMedicines(Integer id){
        PrescriptionMedicines pm=PMR.findPrescriptionMedicicnesByIdPrescription(id);
        if(pm==null){
            return false;
        }
        PMR.delete(pm);
        return true;
    }
    //4:In the event that the type of user disease is commensurate with the type of medication suitable for this disease and the doctor is the one who prescribed the medicine, it is dispensed
    public String GiveDescription(PrescriptionMedicines prescriptionMedicines){
        User user=userRepository.findUserByIdUser(prescriptionMedicines.getIdUser());
        Doctor doctor=doctorRepository.findDoctorByIdDoctor(prescriptionMedicines.getIdDoctor());
Medicines medicines=medicinesRepository.findMedicinesByIdMedicines(prescriptionMedicines.getIdMedicines());
if(user==null){
    return "User Not Found";
}
if(doctor==null){
    return "Doctor Not Found";

}if(medicines==null){
    return "medicines Not Found";
        }
if(doctor.getIdDoctor().equals(prescriptionMedicines.getIdDoctor())){
if(user.getDisease().equals(medicines.getTypeOfDisease())){
    PMR.save(prescriptionMedicines);
    return "The prescription has been issued successfully.";
}
}
return "The prescription was not issued due to the drug not being compatible with the disease.";
    }
//5:In the event that the type of User disease is commensurate with the type of medication suitable for this disease but has not been dispensed by the doctor, a prescription is not accepted
    public String check(PrescriptionMedicines prescriptionMedicines){
        User user=userRepository.findUserByIdUser(prescriptionMedicines.getIdUser());
        Doctor doctor=doctorRepository.findDoctorByIdDoctor(prescriptionMedicines.getIdDoctor());
        Medicines medicines=medicinesRepository.findMedicinesByIdMedicines(prescriptionMedicines.getIdMedicines());
        if(user==null){
            return "User Not Found";
        }
        if(medicines==null){
            return "Medicines Not Found";
        }
        if(doctor==null && medicines.getTypeOfDisease().equals(user.getDisease())) {
            return "The medicine cannot be dispensed because the doctor did not prescribe it.";
        }
        PMR.save(prescriptionMedicines);
            return "Success";
    }
}
