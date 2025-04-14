package com.example.yourcare.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHospital;
    @NotEmpty
    @Size(min = 4)
    @Column(columnDefinition = "varchar(15) not null")
    private String name;
    @NotEmpty
    @Size(min = 10)
@Column(columnDefinition = "varchar(10) not null")
    private String phoneNumber;
    @NotEmpty
  @Email
    private String email;
    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer healthLicense;
    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer idAdmin;
}
