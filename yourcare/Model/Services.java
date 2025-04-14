package com.example.yourcare.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idServices;
    @NotEmpty
    @Pattern(regexp = "Nurse|ambulance|auxiliaryTools")
    @Column(columnDefinition = "varchar(15) not null")
    private String serviceType;
    @PositiveOrZero
    @Column(columnDefinition = "int")
    private Integer numberNurse;
    @Pattern(regexp = "Special Needs Chair|Special chair for the elderly|Bed|null")
    @Column(columnDefinition = "varchar(30) not null")
    private String auxiliaryTools;
    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer idUser;
    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer idHospital;

}
