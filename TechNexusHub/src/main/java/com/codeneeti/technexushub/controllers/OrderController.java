package com.codeneeti.technexushub.controllers;

import com.codeneeti.technexushub.dtos.ApiResponseMessage;
import com.codeneeti.technexushub.dtos.CreateOrderRequest;
import com.codeneeti.technexushub.dtos.OrderDto;
import com.codeneeti.technexushub.dtos.PageableResponse;
import com.codeneeti.technexushub.repositories.OrderRepository;
import com.codeneeti.technexushub.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    //create
    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        OrderDto createdOrder = orderService.creatOrder(request);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResponseMessage> removeOrder(@PathVariable String orderId) {
        orderService.removeOrder(orderId);
        ApiResponseMessage orderDeleted = ApiResponseMessage.builder()
                .message("order deleted")
                .status(HttpStatus.OK)
                .success(true)
                .build();
        return new ResponseEntity<>(orderDeleted, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<OrderDto>> getOrderOfUser(@PathVariable String userId) {
        List<OrderDto> orderDtoList = orderService.getordersOfUser(userId);
        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<OrderDto>> getAllOrder(
            @RequestParam(value = "pageNumber",defaultValue ="0",required = false )int pageNumber,
            @RequestParam(value = "pageSize",defaultValue ="10",required = false )int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "orderDate",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "desc",required = false)String sortDir

    ) {
        PageableResponse<OrderDto> order = orderService.getOrder(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
