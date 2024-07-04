package com.codeneeti.technexushub.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String orderId;
    //PENDING,DISPATCHED,DELIVERED
    //ENUM
    private String orderStatus;
    //NOT-PAID,PAID
    private String paymentStatus;
    private int orderAmount;
    @Column(length = 1000)
    private String billingAddress;
    private String billingName;
    private String billingPhone;
    private Date orderDate;
    private Date deliverdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<OrderItem>orderItems=new ArrayList<>();
}
