package com.example.yourcare.Repository;

import com.example.yourcare.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
    @Query("select r from Appointment r where r.idReservation=?1")
    Appointment findReservationsByIdReservation(Integer id);
    List<Appointment> findReservationsByIdDoctor(Integer id);
    List<Appointment>findReservationsByIdUser(Integer id);
    Appointment findReservationsByReservationType(String type);

}
