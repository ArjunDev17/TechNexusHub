package com.codeneeti.technexushub.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserAddressDto address;
    private String profilePicture;
    private String dob; // Date of Birth
    private String gender;
    private String collegeName;
    private String course;
    private String yearOfStudy;
    private String cgpa;
    private String interest; // Fixed typo from "intrest" to "interest"
    private String skill;
    private String linkdn;  // LinkedIn link
    private String github;  // GitHub link
}
