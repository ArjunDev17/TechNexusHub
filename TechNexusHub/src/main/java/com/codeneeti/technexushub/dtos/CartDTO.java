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
public class CartDTO {
    private String cartId;
    private Date createdAt;
    //    private String userName;
    private UserDTO user;
    private List<CartItemDTO> items = new ArrayList<>();
}
