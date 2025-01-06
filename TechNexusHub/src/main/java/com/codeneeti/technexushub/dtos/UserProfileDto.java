package com.codeneeti.technexushub.dtos;

import com.codeneeti.technexushub.entities.UserAddress;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "profile")
public class UserProfileDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    @Column(nullable = false)
    private String phone;

    private String firstName;
    private String lastName;

    @Embedded
    private UserAddressDto address;

//    @Temporal(TemporalType.DATE)
    private String dob;

    private String gender;
    private String profilePicture;

    private String collegeName;
    private String course;

    @Column(nullable = true)
    private String yearOfStudy;

    @Column(nullable = true)
    private String cgpa;

    private String interest;
    private String skill;

    @Column(nullable = true)
    private String linkdn;

    @Column(nullable = true)
    private String github;

}

