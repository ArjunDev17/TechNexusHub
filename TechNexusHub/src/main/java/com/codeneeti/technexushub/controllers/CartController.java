package com.codeneeti.technexushub.controllers;

import com.codeneeti.technexushub.dtos.AddItemToCartRequest;
import com.codeneeti.technexushub.dtos.ApiResponseMessage;
import com.codeneeti.technexushub.dtos.CartDto;
import com.codeneeti.technexushub.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;


@PostMapping(value = "/add/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<CartDto> addItemToCart(@PathVariable String userId,
                                             @RequestBody AddItemToCartRequest request) {
    CartDto cartDTO = cartService.addItemToCart(userId, request);
    return new ResponseEntity<>(cartDTO, HttpStatus.OK);
}


    @DeleteMapping("/{userId}/items/{itemId}")
    public ResponseEntity<ApiResponseMessage> removeItemfromCart(@PathVariable String userId,
                                                                 @PathVariable int itemId) {
        cartService.removeItemFromCart(userId, itemId);
        ApiResponseMessage apiResponse = ApiResponseMessage.builder()
                .message("item removed")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //clear cart
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        ApiResponseMessage apiResponse = ApiResponseMessage.builder()
                .message("all cart removed")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable("userId") String userId) {
        CartDto cartDTO = cartService.getCartByUser(userId);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }
}
