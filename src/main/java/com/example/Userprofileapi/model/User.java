package com.example.Userprofileapi.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data                   // generates getX/setX/toString/equals/hashCode
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;

    private String name;

    @Column(unique = true)
    private String email;

    private LocalDateTime registrationDate;
}
