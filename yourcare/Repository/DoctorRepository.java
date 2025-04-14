package com.example.yourcare.Repository;

import com.example.yourcare.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    @Query("select d from Doctor d where d.idDoctor=?1")
    Doctor findDoctorByIdDoctor(Integer id);
    @Query("select d from Doctor d where d.specialization=?1")
    List<Doctor>findDoctorBySpecialization(String Specialization);
}
