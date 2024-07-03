package com.codeneeti.technexushub.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddItemToCartRequest {
    private String  productId;
    private int quantity;
}
