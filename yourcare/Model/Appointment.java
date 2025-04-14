package com.example.yourcare.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReservation;
    @NotEmpty
    @Pattern(regexp = "Onlin|Attendance")
    @Column(columnDefinition = "varchar(13) not null")
    private String reservationType;
//    @Pattern(regexp = "yyyy-mm-dd")
    private Date reservationDate;
    @NotNull
    private Integer idUser;
    @NotNull
    private Integer idDoctor;
    @NotNull
    private Integer idHospital;
    private String link;

}
