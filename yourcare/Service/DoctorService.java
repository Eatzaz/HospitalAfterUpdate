package com.example.yourcare.Service;

import com.example.yourcare.Model.Doctor;
import com.example.yourcare.Repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    public List<Doctor>getAlldoctor(){
       List<Doctor> doctors=doctorRepository.findAll();
        return doctors;
    }
    public void addDoctor(Doctor doctor){
        doctorRepository.save(doctor);
    }
    public boolean updateDoctor(Integer id,Doctor doctor){
        Doctor doctor1=doctorRepository.findDoctorByIdDoctor(id);
        if(doctor1==null){
            return false;
        }
        doctor1.setName(doctor.getName());
        doctor1.setEmail(doctor.getEmail());
        doctor1.setPhoneNumber(doctor.getPhoneNumber());
        doctor1.setSpecialization(doctor.getSpecialization());
        doctorRepository.save(doctor1);
        return true;
    }
    public boolean deleteDoctor(Integer id){
        Doctor doctor=doctorRepository.findDoctorByIdDoctor(id);
        if(doctor==null){
            return false;
        }
        doctorRepository.delete(doctor);
        return true;
    }
}
