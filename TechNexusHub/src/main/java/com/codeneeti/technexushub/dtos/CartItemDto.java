package com.codeneeti.technexushub.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDto {
    private int cartItemId;
    private int quantity;
    private double totalPrice;
    private ProductDto product;
}
