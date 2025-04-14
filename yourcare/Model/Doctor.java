package com.example.yourcare.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDoctor;
    @NotEmpty
    @Size(min=3)
    @Column(columnDefinition = "varchar(15) not null")
    private String name;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(max = 10)
    @Column(columnDefinition = "varchar(10) not null")
    private String phoneNumber;
    @NotEmpty
    @Size(min = 3)
    @Column(columnDefinition = "varchar(20) not null")
    private String specialization;
    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer licensePracticeProfession;
}
