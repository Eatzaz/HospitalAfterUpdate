package com.example.yourcare.Repository;

import com.example.yourcare.Model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesRepository extends JpaRepository<Services,Integer> {
    @Query("select s from Services s where s.idServices=?1")
    Services findServicesByIdServices(Integer id);
    @Query("select s from Services s where s.idUser=?1")
    Services findServicesByIdUser(Integer id);
    @Query("select s from Services s where s.auxiliaryTools=?1")
    List<Services>findServicesByAuxiliaryTools(String Tools);
}
