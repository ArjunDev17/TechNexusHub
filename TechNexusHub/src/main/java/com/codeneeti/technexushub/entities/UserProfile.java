package com.codeneeti.technexushub.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phone;

    private String firstName;
    private String lastName;

    @Embedded
    private UserAddress address;

    private String dob; // Date of Birth
    private String gender;
    private String profilePicture;
    private String collegeName;
    private String course;
    private String yearOfStudy;
    private String cgpa;

    private String interest; // Fixed typo: "intrest" -> "interest"
    private String skill;
    private String linkdn;  // LinkedIn link
    private String github;  // GitHub link
}
