package com.example.yourcare.Service;

import com.example.yourcare.Model.Admin;
import com.example.yourcare.Model.Doctor;
import com.example.yourcare.Repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    public List<Admin> getAdmin(){
        return adminRepository.findAll();
    }
    public void addAdmin(Admin admin){
adminRepository.save(admin);
    }
    public boolean updateAdmin(Integer id,Admin admin){
        Admin admin1=adminRepository.findAllById(id);
        if(admin1==null){
            return false;
        }
       admin1.setName(admin.getName());
        return true;
    }
    public boolean deleteAdmin(Integer id){
        Admin admin=adminRepository.findAllById(id);
        if(admin==null){
            return false;
        }
        adminRepository.delete(admin);
        return true;
    }
}
