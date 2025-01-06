package com.codeneeti.technexushub.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddressDto {

    private String street;
    private String city;
    private String state;
    private String zipCode;
}
