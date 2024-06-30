package com.codeneeti.technexushub.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDTO {
    private String productId;
    private String title;
    private String description;
    private int price ;
    private int discountPrice;
    private int quantity;
//    private Date addedDate;
    private Date addedDate;
    private boolean live;
    private boolean stock;
    private String productImageName;
}
