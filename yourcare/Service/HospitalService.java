package com.example.yourcare.Service;

import com.example.yourcare.Model.Admin;
import com.example.yourcare.Model.Hospital;
import com.example.yourcare.Model.Medicines;
import com.example.yourcare.Model.Stack;
import com.example.yourcare.Repository.AdminRepository;
import com.example.yourcare.Repository.HospitalRepository;
import com.example.yourcare.Repository.StackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;
    private final AdminRepository adminRepository;
    public List<Hospital>getHospital(){
        return hospitalRepository.findAll();
    }
    public String addHospital(Hospital hospital){
        Admin admin=adminRepository.findAllById(hospital.getIdAdmin());
        if(admin==null){
            return "admin Not Exist";
        }
        hospitalRepository.save(hospital);
        return "Success";
    }
    public boolean updateHospital(Integer id, Hospital hospital){
Hospital hospital1=hospitalRepository.findHospitalByIdHospitalIs(id);
        if(hospital1==null){
            return false;
        }
       hospital1.setName(hospital.getName());
        hospital1.setEmail(hospital.getEmail());
        hospital1.setPhoneNumber(hospital.getPhoneNumber());
        hospital1.setIdAdmin(hospital.getIdAdmin());
        hospitalRepository.save(hospital1);
        return true;
    }
    public boolean deleteHospital(Integer id){
Hospital hospital=hospitalRepository.findHospitalByIdHospitalIs(id);
    if(hospital==null){
            return false;
        }
hospitalRepository.delete(hospital);
    return true;
    }
}
