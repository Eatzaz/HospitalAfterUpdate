package com.example.yourcare.Service;

import com.example.yourcare.Model.*;
import com.example.yourcare.Repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final StackRepository stackRepository;
    public List<Appointment>getAllReservation(){
        return appointmentRepository.findAll();
    }
   public String addReservation(Appointment appointment){
        User user=userRepository.findUserByIdUser(appointment.getIdUser());
        Doctor doctor=doctorRepository.findDoctorByIdDoctor(appointment.getIdDoctor());
        Hospital hospital=hospitalRepository.findHospitalByIdHospitalIs(appointment.getIdHospital());
        if(user==null){
            return "The User Is Not Found";
        }
        if(doctor==null){
            return "The Doctor Is Not Found";
        } if(hospital==null){
           return "The Hospital Is Not Found";
       }
        appointmentRepository.save(appointment);
        return "true";
   }
    public boolean updateReservations(Integer id, Appointment appointment){
        Appointment appointment1 = appointmentRepository.findReservationsByIdReservation(id);
        if(appointment1 ==null){
            return false;
        }
        appointment1.setReservationType(appointment.getReservationType());
        appointment1.setReservationDate(appointment.getReservationDate());
        appointment1.setIdUser(appointment.getIdUser());
        appointment1.setIdDoctor(appointment.getIdDoctor());
        appointment1.setIdHospital(appointment.getIdHospital());
        appointmentRepository.save(appointment1);
        return true;
    }
    public boolean deleteReservations(Integer id){
        Appointment appointment = appointmentRepository.findReservationsByIdReservation(id);
        if(appointment ==null){
            return false;
        }
        appointmentRepository.delete(appointment);
        return true;
    }
//1:If the user disease is suitable for the doctor's specialty, an appointment is booked with the doctor
public String reservations(Appointment appointment){
        User user=userRepository.findUserByIdUser(appointment.getIdUser());
    Doctor doctor=doctorRepository.findDoctorByIdDoctor(appointment.getIdDoctor());
    Hospital hospital=hospitalRepository.findHospitalByIdHospitalIs(appointment.getIdHospital());
    if(user==null){
        return "User Not Found";
    }
    if (doctor==null){
        return "Doctor Not Found";
    }if(hospital==null){
        return "The Hospital Is Not Found";
    }
    if(user.getDisease().equals(doctor.getSpecialization())&&appointment.getReservationType().equals("Attendance")){
        appointmentRepository.save(appointment);
        return "An appointment has been booked with the doctor Attendance";
    }if(user.getDisease().equals(doctor.getSpecialization())&& appointment.getReservationType().equals("Onlin")){
        appointmentRepository.save(appointment);
        return "An appointment has been booked with the doctor online." +
                "This is Link: https://zoom.us/4554332";
    }
    return "There is no doctor for this disease";
}

//3 :If an appointment is requested with a doctor whose specialty matches the type of illness but he is not available,
// an appointment is made with an emergency doctor.
    public String EmergencyReservation(Appointment appointment){
        Doctor doctor=doctorRepository.findDoctorByIdDoctor(appointment.getIdDoctor());
        User user=userRepository.findUserByIdUser(appointment.getIdUser());
        Hospital hospital=hospitalRepository.findHospitalByIdHospitalIs(appointment.getIdHospital());
        Stack stack=stackRepository.findServiceStoreById(hospital.getIdHospital());
        if(user==null){
            return "User Not Found";
        }if(hospital==null){
            return "The Hospital Is Not Found";
        }
        if(doctor==null&& appointment.getReservationType().equals("Onlin")&&stack.getEmergencyDoctor()>=1){
           stack.setEmergencyDoctor(stack.getEmergencyDoctor()-1);
            appointmentRepository.save(appointment);
            return "The doctor is currently unavailable, an appointment has been made with the emergency doctor.";
        }if(doctor!=null){
        appointmentRepository.save(appointment);
        return "An appointment has been booked with the doctor.";}
        return "Valid";
    }
}
