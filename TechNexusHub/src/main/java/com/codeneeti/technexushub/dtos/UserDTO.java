package com.codeneeti.technexushub.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String userId;
    private String name;
    private String email;
    private String password;
    private String gender;
   private String about;
    private String imageName;
}
