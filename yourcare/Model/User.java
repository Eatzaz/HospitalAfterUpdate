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
public class User {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;
@NotEmpty
@Size(min = 3)
@Column(columnDefinition = "varchar(15) not null")
private String name;
@NotNull
@Column(columnDefinition = "int not null")
private Integer age;
@Email
private String email;
@NotEmpty
@Size(max = 10)
@Column(columnDefinition = "varchar(10) not null")
private String phoneNumber;
@NotEmpty
@Size(max = 15)
@Pattern(regexp = "Elderly|Spical needs")
@Column(columnDefinition = "varchar(15) not null")
private String status;
@NotEmpty
@Size(min = 5)
@Column(columnDefinition = "varchar(20) not null")
private String disease;

}
