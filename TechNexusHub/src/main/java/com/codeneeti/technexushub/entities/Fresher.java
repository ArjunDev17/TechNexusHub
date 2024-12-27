package com.codeneeti.technexushub.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "freshers")
public class Fresher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fresher_id")
    private Long fresherId;  // Unique ID for each fresher

    @Column(name = "first_name", nullable = false)
    private String firstName;  // First Name

    @Column(name = "last_name", nullable = false)
    private String lastName;  // Last Name

    @Column(name = "email", nullable = false, unique = true)
    private String email;  // Email address (unique)

    @Column(name = "phone_number")
    private String phoneNumber;  // Phone number (nullable)

//    @Column(name = "username", nullable = false, unique = true)
//    private String username;  // Username (unique)

    @Column(name = "password", nullable = false)
    private String password;  // Password

//    @Column(name = "confirm_password", nullable = false)
//    private String confirmPassword;  // Confirm password

//    // Optional fields
//    @Column(name = "gender")
//    private String gender;  // Gender (optional)
}
