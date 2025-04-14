package com.example.yourcare.Model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMeeting;
private Date meetingDate;
    @NotEmpty
    @Column(columnDefinition = "varchar(23) not null")
private String link;
    @NotNull
    private Integer idReservation;
    @NotNull
    private Integer idUser;
    @NotNull
    private Integer idDoctor;
//    @NotNull
//    private Integer idStack;
}
