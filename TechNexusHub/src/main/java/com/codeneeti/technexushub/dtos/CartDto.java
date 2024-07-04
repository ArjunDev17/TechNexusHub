package com.codeneeti.technexushub.dtos;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {
    private String cartId;
    private Date createdAt;
    //    private String userName;
    private UserDto user;
    private List<CartItemDto> items = new ArrayList<>();
}
