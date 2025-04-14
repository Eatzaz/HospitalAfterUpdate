package com.example.yourcare.Repository;

import com.example.yourcare.Model.PrescriptionMedicines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionMedicinesRepository extends JpaRepository<PrescriptionMedicines,Integer> {
    @Query("select p from PrescriptionMedicines p where p.idPrescription=?1")
    PrescriptionMedicines findPrescriptionMedicicnesByIdPrescription(Integer id);
}
