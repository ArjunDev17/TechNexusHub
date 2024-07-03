package com.codeneeti.technexushub.controllers;

import com.codeneeti.technexushub.dtos.AddItemToCartRequest;
import com.codeneeti.technexushub.dtos.ApiResponse;
import com.codeneeti.technexushub.dtos.CartDTO;
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
public ResponseEntity<CartDTO> addItemToCart(@PathVariable String userId,
                                             @RequestBody AddItemToCartRequest request) {
    CartDTO cartDTO = cartService.addItemToCart(userId, request);
    return new ResponseEntity<>(cartDTO, HttpStatus.OK);
}


    @DeleteMapping("/{userId}/items/{itemId}")
    public ResponseEntity<ApiResponse> removeItemfromCart(@PathVariable String userId,
                                                          @PathVariable int itemId) {
        cartService.removeItemFromCart(userId, itemId);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("item removed")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //clear cart
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("all cart removed")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable("userId") String userId) {
        CartDTO cartDTO = cartService.getCartByUser(userId);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }
}
