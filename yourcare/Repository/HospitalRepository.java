package com.example.yourcare.Repository;

import com.example.yourcare.Model.Hospital;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Integer> {
    @Query("select h from Hospital h where h.idHospital=?1")
    Hospital findHospitalByIdHospitalIs(Integer id);
}
