package com.example.yourcare.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Stack {
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    @PositiveOrZero
    @Column(columnDefinition = "int not null")
    private Integer numberNurse;
    @NotNull
    @PositiveOrZero
    @Column(columnDefinition = "int not null")
    private Integer numberAmbulance;
    @NotNull
    @PositiveOrZero
    @Column(columnDefinition = "int not null")
private Integer emergencyDoctor;
    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer idHospital;

}
