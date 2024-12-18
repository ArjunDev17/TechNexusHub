package com.codeneeti.technexushub.controllers;

// DTOs
import com.codeneeti.technexushub.dtos.AddItemToCartRequest;
import com.codeneeti.technexushub.dtos.ApiResponse;
import com.codeneeti.technexushub.dtos.CartDTO;

// Services
import com.codeneeti.technexushub.services.CartService;

// Spring Framework
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

    /**
     * Add an item to the cart for a specific user.
     *
     * @param userId  the ID of the user
     * @param request the details of the item to be added
     * @return the updated cart as a DTO
     */
    @PostMapping(value = "/add/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartDTO> addItemToCart(@PathVariable String userId,
                                                 @RequestBody AddItemToCartRequest request) {
        CartDTO cartDTO = cartService.addItemToCart(userId, request);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    /**
     * Remove a specific item from the cart for a given user.
     *
     * @param userId the ID of the user
     * @param itemId the ID of the item to be removed
     * @return an API response indicating success
     */
    @DeleteMapping("/{userId}/items/{itemId}")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable String userId,
                                                          @PathVariable int itemId) {
        cartService.removeItemFromCart(userId, itemId);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Item removed successfully")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Clear the entire cart for a specific user.
     *
     * @param userId the ID of the user
     * @return an API response indicating success
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("All items removed from cart")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Retrieve the cart details for a specific user.
     *
     * @param userId the ID of the user
     * @return the cart details as a DTO
     */
    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable("userId") String userId) {
        CartDTO cartDTO = cartService.getCartByUser(userId);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }
}
