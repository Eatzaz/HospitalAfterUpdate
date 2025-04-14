package com.example.yourcare.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class PrescriptionMedicines {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrescription;
    @NotNull
    private Integer idUser;
    @NotEmpty
    @Column(columnDefinition = "varchar(40) not null")
    private String prescription;
    @NotNull
    private Integer idDoctor;
    @NotNull
    private Integer idMedicines;

}
