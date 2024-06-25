package com.codeneeti.technexushub.dtos;

import com.codeneeti.technexushub.validate.ImageNameValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    //pattern
    //custome validator
    private String userId;
    @Size(min = 3,max = 15,message = "Invalid name")
    private String name;
//    @Email(message="Invalid email")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email")
    private String email;
    @NotBlank(message = "Password required")
    private String password;
    @Size(min=2,max=6,message = "Invalid gender data")
    private String gender;
    @NotBlank(message = "please enter few information")
   private String about;
    @ImageNameValid
   private String imageName;
//    @ImageNameValid this is user defined annotation

}
