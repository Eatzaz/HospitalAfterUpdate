package com.example.yourcare.Repository;

import com.example.yourcare.Model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting,Integer> {
    @Query("select m from Meeting m where m.idMeeting=?1")
    Meeting findMeetingByIdMeeting(Integer id);
}
