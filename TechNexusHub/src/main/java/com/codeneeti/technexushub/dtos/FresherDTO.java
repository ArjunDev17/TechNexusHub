package com.codeneeti.technexushub.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FresherDTO {

    private String fresherId;  // This is the unique ID but it could be auto-generated, so it's optional in DTO

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;  // First name of the fresher

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;  // Last name of the fresher

    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String email;  // Email address of the fresher

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;  // Optional field, phone number should be 10 digits, if provided

//    @NotBlank(message = "Username is required")
//    @Size(min = 3, max = 15, message = "Username must be between 3 and 15 characters")
//    private String username;  // Unique username for the fresher

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;  // Password

//    @NotBlank(message = "Confirm password is required")
//    @Size(min = 6, max = 20, message = "Confirm password must be between 6 and 20 characters")
//    private String confirmPassword;  // Confirm password for verification

//    @Size(min = 2, max = 6, message = "Gender should be between 2 and 6 characters")
//    private String gender;  // Gender of the fresher (optional)

}
