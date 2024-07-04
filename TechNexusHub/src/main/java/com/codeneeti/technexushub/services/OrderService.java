package com.codeneeti.technexushub.services;

import com.codeneeti.technexushub.dtos.CreateOrderRequest;
import com.codeneeti.technexushub.dtos.OrderDto;
import com.codeneeti.technexushub.dtos.PageableResponse;

import java.util.List;

public interface OrderService {
    //create order
    OrderDto creatOrder(CreateOrderRequest orderDto);
    //remove order
    void removeOrder(String orderId);

    //get order of users
    List<OrderDto>getordersOfUser(String userId);
    //get order
    PageableResponse<OrderDto>getOrder(int pageNumber,
                                       int pageSize,
                                       String sortBy,
                                       String sortDir);


}
