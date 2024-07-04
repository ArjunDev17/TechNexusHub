package com.codeneeti.technexushub.services.impl;

import com.codeneeti.technexushub.dtos.CreateOrderRequest;
import com.codeneeti.technexushub.dtos.OrderDto;
import com.codeneeti.technexushub.dtos.PageableResponse;
import com.codeneeti.technexushub.entities.*;
import com.codeneeti.technexushub.exceptions.BadApiRequestException;
import com.codeneeti.technexushub.exceptions.ResourceNotFoundException;
import com.codeneeti.technexushub.helper.Helper;
import com.codeneeti.technexushub.repositories.CartRepository;
import com.codeneeti.technexushub.repositories.OrderRepository;
import com.codeneeti.technexushub.repositories.UserRepository;
import com.codeneeti.technexushub.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ModelMapper mapper;
//    @Override
    public OrderDto creatOrder1(CreateOrderRequest orderDto) {
        String userId=orderDto.getUserId();
        String cartId=orderDto.getCartId();
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user not found")
        );
        Cart cart = cartRepository.findById(cartId).orElseThrow(
                () -> new ResourceNotFoundException("cart not found")
        );
        List<CartItem> cartItems = cart.getItems();
        if (cartItems.size()<=0){
            throw new BadApiRequestException("invalid number of expression");
        }
        Order order = Order.builder()
                .billingName(orderDto.getBillingName())
                .billingPhone(orderDto.getBillingPhone())
                .billingAddress(orderDto.getBillingAddress())
                .orderDate(new Date())
                .deliverdDate(null)
                .paymentStatus(orderDto.getPaymentStatus())
                .orderStatus(orderDto.getOrderStatus())
                .orderId(UUID.randomUUID().toString())
                .user(user)
                .build();
        //orderItem,Amount
        AtomicReference<Integer>orderAmount=new AtomicReference<>(0);
        List<OrderItem> orderItems = cartItems.stream().map(cartItem ->
                {
//                    CartItem--->OrderItem
                    OrderItem orderItem = OrderItem.builder()
                            .quantity(cartItem.getQuantity())
                            .product(cartItem.getProduct())
                            .totalPrice(cartItem.getQuantity() * cartItem.getProduct().getDiscountPrice())
                            .order(order)
                            .build();
                    orderAmount.set(orderAmount.get()+orderItem.getTotalPrice());
                    return orderItem;
                }
        ).collect(Collectors.toList());

        order.setOrderItems(orderItems);
        order.setOrderAmount(orderAmount.get());

        cart.getItems().clear();
        cartRepository.save(cart);
        Order saved = orderRepository.save(order);

        return mapper.map(saved,OrderDto.class);
    }


    @Override
    public OrderDto creatOrder(CreateOrderRequest orderDto) {

        String userId = orderDto.getUserId();
        String cartId = orderDto.getCartId();
        //fetch user
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given id !!"));

        //fetch cart
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart with given id not found on server !!"));

        List<CartItem> cartItems = cart.getItems();

        if (cartItems.size() <= 0) {
            throw new BadApiRequestException("Invalid number of items in cart !!");

        }

        //other checks

        Order order = Order.builder()
                .billingName(orderDto.getBillingName())
                .billingPhone(orderDto.getBillingPhone())
                .billingAddress(orderDto.getBillingAddress())
                .orderDate(new Date())
                .deliverdDate(null)
                .paymentStatus(orderDto.getPaymentStatus())
                .orderStatus(orderDto.getOrderStatus())
                .orderId(UUID.randomUUID().toString())
                .user(user)
                .build();

//        orderItems,amount

        AtomicReference<Integer> orderAmount = new AtomicReference<>(0);
        List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {
//            CartItem->OrderItem
            OrderItem orderItem = OrderItem.builder()
                    .quantity(cartItem.getQuantity())
                    .product(cartItem.getProduct())
                    .totalPrice(cartItem.getQuantity() * cartItem.getProduct().getDiscountPrice())
                    .order(order)
                    .build();

            orderAmount.set(orderAmount.get() + orderItem.getTotalPrice());
            return orderItem;
        }).collect(Collectors.toList());


        order.setOrderItems(orderItems);
        order.setOrderAmount(orderAmount.get());

        System.out.println(order.getOrderItems().size());

        //
        cart.getItems().clear();
        cartRepository.save(cart);
        Order savedOrder = orderRepository.save(order);
        return mapper.map(savedOrder, OrderDto.class);
    }

    @Override
    public void removeOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("order not found")
        );
        orderRepository.delete(order);
    }

    @Override
    public List<OrderDto> getordersOfUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("order not found")
        );
        List<Order> orders = orderRepository.findByUser(user);
        List<OrderDto> orderDtos = orders.stream().map(
                order -> mapper.map(order, OrderDto.class)).collect(Collectors.toList()
        );

        return orderDtos;

    }

    @Override
    public PageableResponse<OrderDto> getOrder(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Order> page = orderRepository.findAll(pageable);
        return Helper.getPageableResponse(page,OrderDto.class);
    }
}
