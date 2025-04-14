package com.example.yourcare.Repository;

import com.example.yourcare.Model.Admin;
import com.example.yourcare.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository
        extends JpaRepository<Admin,Integer> {
    @Query("select r from Admin r where r.id=?1")
    Admin findAllById(Integer id);

}
