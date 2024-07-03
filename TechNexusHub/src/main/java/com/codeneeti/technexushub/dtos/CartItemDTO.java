package com.codeneeti.technexushub.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDTO {
    private int cartItemId;
    private int quantity;
    private double totalPrice;
    private ProductDTO product;
}
