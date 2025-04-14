package com.example.yourcare.Service;

import com.example.yourcare.Model.*;
import com.example.yourcare.Repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository repository;
    private final StackRepository stackRepository;

    public List<Meeting>getAllMeeting(){
        return meetingRepository.findAll();
    }
    public String addMeeting(Meeting meeting){
      User user =userRepository.findUserByIdUser(meeting.getIdUser());
      Doctor doctor= doctorRepository.findDoctorByIdDoctor(meeting.getIdDoctor());
      Appointment appointment = repository.findReservationsByIdReservation(meeting.getIdReservation());
        if(user==null){
           return "User Not Found";
       }
       if(doctor==null){
           return "Doctor Not Found";
       }
       if(appointment ==null){
           return "Reservations is Not Exist";
       }
       if(appointment.getReservationType().equals("Onlin")){
       meetingRepository.save(meeting);
       return "Success";
       }
       return "Not booked";
    }
    public boolean updateMeeting(Integer id, Meeting meeting){
Meeting meeting1=meetingRepository.findMeetingByIdMeeting(id);
    if(meeting1==null){
            return false;
        }
        meeting1.setLink(meeting.getLink());
    meeting1.setMeetingDate(meeting.getMeetingDate());
    meeting1.setIdUser(meeting.getIdUser());
    meeting1.setIdDoctor(meeting.getIdDoctor());
    meeting1.setIdReservation(meeting.getIdReservation());
    meetingRepository.save(meeting1);
        return true;
    }
    public boolean deleteMeeting(Integer id){
        Meeting meeting=meetingRepository.findMeetingByIdMeeting(id);
    if(meeting==null){
            return false;
        }
        meetingRepository.delete(meeting);
        return true;
    }


}
