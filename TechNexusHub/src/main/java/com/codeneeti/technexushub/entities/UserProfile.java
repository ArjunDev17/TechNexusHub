package com.codeneeti.technexushub.entities;

import com.codeneeti.technexushub.entities.UserAddress;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.data.jpa.repository.Temporal;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(nullable = false, unique = true)
    private String email;  // Ensuring unique email for the profile

    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    @Column(nullable = false)
    private String phone;  // Phone number (should be validated)

    private String firstName;
    private String lastName;

    @Embedded
    private UserAddress address;

    private String dob;  // Date of Birth (changed to Date type for proper handling)

    private String gender;
    private String profilePicture;

    private String collegeName;
    private String course;

    @Column(nullable = true)
    private String yearOfStudy;  // Year of study (changed to Integer)

    @Column(nullable = true)
    private String cgpa;  // CGPA (changed to Double for better precision)

    private String interest;  // Corrected typo: "intrest" -> "interest"
    private String skill;

    @Column(nullable = true)
    private String linkdn;  // LinkedIn link

    @Column(nullable = true)
    private String github;  // GitHub link
}
