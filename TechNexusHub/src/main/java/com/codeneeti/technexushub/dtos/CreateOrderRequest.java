package com.codeneeti.technexushub.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderRequest {
    @NotBlank(message = "cartId is mandatory")
   private String cartId;
    @NotBlank(message = "userId is mandatory")
   private String userId;

    private String orderStatus="PENDING";
    private String paymentStatus="NOTPAID";
    @NotBlank(message = "billingAddress is mandatory")
    private String billingAddress;
    @NotBlank(message = "billingPhone is mandatory")
    private String billingPhone;
    @NotBlank(message = "billingName is mandatory")
    private String billingName;

}
