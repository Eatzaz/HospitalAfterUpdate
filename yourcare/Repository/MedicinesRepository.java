package com.example.yourcare.Repository;

import com.example.yourcare.Model.Medicines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicinesRepository extends JpaRepository<Medicines,Integer> {
    @Query("select m from Medicines m where m.idMedicines=?1")
    Medicines findMedicinesByIdMedicines(Integer id);
}
