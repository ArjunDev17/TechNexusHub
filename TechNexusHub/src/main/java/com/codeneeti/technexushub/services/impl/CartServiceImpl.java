package com.codeneeti.technexushub.services.impl;
import com.codeneeti.technexushub.dtos.AddItemToCartRequest;
import com.codeneeti.technexushub.dtos.CartDto;
import com.codeneeti.technexushub.entities.*;
import com.codeneeti.technexushub.exceptions.BadApiRequestException;
import com.codeneeti.technexushub.exceptions.ResourceNotFoundException;
import com.codeneeti.technexushub.repositories.CartItemRepository;
import com.codeneeti.technexushub.repositories.CartRepository;
import com.codeneeti.technexushub.repositories.ProductRepository;
import com.codeneeti.technexushub.repositories.UserRepository;
import com.codeneeti.technexushub.services.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CartItemRepository cartItemRepository;

//    @Override
    public CartDto addItemToCart1(String userId, AddItemToCartRequest request) {
        String productId = request.getProductId();
        int quantity = request.getQuantity();
        if (quantity<=0){
            throw new BadApiRequestException("Requested quantity is not VALID");
        }
        //fetch the product
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("based on id product is not available")
        );
        //fetch user for cart
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("based on id user is not available")
        );

        Cart cart=null;
        try {
             cart = cartRepository.findByUser(user).get();
        } catch (NoSuchElementException exception) {
            cart = new Cart();
            cart.setCartId(UUID.randomUUID().toString());
            cart.setCreatedAt(new Date());
        }
        //perform cart opearations
        //if cart item alredy present
        AtomicReference<Boolean> updated = new AtomicReference<>(false);
        List<CartItem> items = cart.getItems();
        items  = items.stream().map(item -> {
            if (item.getProduct().getProductId().equals(productId)) {
                //item already present in cart
                item.setQuantity(quantity);
                item.setTotalPrice(quantity * product.getDiscountPrice());
                updated.set(true);
            }
            return item;
        }).collect(Collectors.toList());
//        cart.setItems(updateItem);
        if (!updated.get()) {
            CartItem cartItems = CartItem.builder()
                    .quantity(quantity)
                    .totalPrice(quantity * product.getDiscountPrice())
                    .product(product)
                    .build();
            cart.getItems().add(cartItems);
        }

        cart.setUser(user);
        Cart savedCart = cartRepository.save(cart);
        return modelMapper.map(savedCart, CartDto.class);
    }


//    @Override
    public CartDto addItemToCart2(String userId, AddItemToCartRequest request) {
        String productId = request.getProductId();
        int quantity = request.getQuantity();
        if (quantity <= 0) {
            throw new BadApiRequestException("Requested quantity is not VALID");
        }

        // Fetch the product
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product not available for the given ID")
        );

        // Fetch user for cart
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not available for the given ID")
        );
        Cart cart1 = cartRepository.findByUser(user).get();
        // Fetch or create cart for user
        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setCartId(UUID.randomUUID().toString());
            newCart.setCreatedAt(new Date());
            return newCart;
        });

        // Perform cart operations
        AtomicReference<Boolean> updated = new AtomicReference<>(false);
        List<CartItem> items = cart.getItems();

        // Update item if already present in the cart
        List<CartItem> updatedItems = items.stream().peek(item -> {
            if (item.getProduct().getProductId().equals(productId)) {
                item.setQuantity(quantity);
                item.setTotalPrice(quantity * product.getDiscountPrice());
                updated.set(true);
            }
        }).collect(Collectors.toList());
        cart.setItems(updatedItems);

        // Add new item if not already present in the cart
        if (!updated.get()) {
            CartItem newItem = CartItem.builder()
                    .quantity(quantity)
                    .totalPrice(quantity * product.getDiscountPrice())
                    .product(product)
                    .build();
            cart.getItems().add(newItem);
        }

        cart.setUser(user);
        Cart savedCart = cartRepository.save(cart);
        // Customize mappings if necessary


        return modelMapper.map(savedCart, CartDto.class);
    }



//    @Override
    public CartDto addItemToCart3(String userId, AddItemToCartRequest request) {

        int quantity = request.getQuantity();
        String productId = request.getProductId();

        if (quantity <= 0) {
            throw new BadApiRequestException("Requested quantity is not valid !!");
        }

        //fetch the product
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found in database !!"));
        //fetch the user from db
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found in database!!"));

        Cart cart = null;
        try {
            System.out.println("coming inside");
            cart = cartRepository.findByUser(user).get();
        } catch (NoSuchElementException e) {
            System.out.println("coming inside 2");
            cart = new Cart();
            cart.setCartId(UUID.randomUUID().toString());
            cart.setCreatedAt(new Date());
        }

        //perform cart operations
        //if cart items already present; then update
        AtomicReference<Boolean> updated = new AtomicReference<>(false);
        List<CartItem> items = cart.getItems();
        items = items.stream().map(item -> {

            if (item.getProduct().getProductId().equals(productId)) {
                //item already present in cart
                item.setQuantity(quantity);
                item.setTotalPrice(quantity * product.getDiscountPrice());
                updated.set(true);
            }
            return item;
        }).toList();

//        cart.setItems(updatedItems);

        //create items
        if (!updated.get()) {
            CartItem cartItem = CartItem.builder()
                    .quantity(quantity)
                    .totalPrice(quantity * product.getDiscountPrice())
                    .cart(cart)
                    .product(product)
                    .build();
            cart.getItems().add(cartItem);
        }


        cart.setUser(user);
        Cart updatedCart = cartRepository.save(cart);
        return modelMapper.map(updatedCart, CartDto.class);


    }


//    @Override
    public CartDto addItemToCart4(String userId, AddItemToCartRequest request) {
        int quantity = request.getQuantity();
        String productId = request.getProductId();

        if (quantity <= 0) {
            throw new BadApiRequestException("Requested quantity is not valid !!");
        }

        // Fetch the product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found in database !!"));
        // Fetch the user from the database
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found in database !!"));

        // Fetch the user's cart or create a new one if it doesn't exist
        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setCartId(UUID.randomUUID().toString());
            newCart.setCreatedAt(new Date());
            newCart.setUser(user);
            return newCart;
        });

        // Check if the product is already in the cart and update the quantity
        AtomicReference<Boolean> updated = new AtomicReference<>(false);
        List<CartItem> items = cart.getItems();
        items = items.stream().map(item -> {
            if (item.getProduct().getProductId().equals(productId)) {
                // Item already present in the cart
                item.setQuantity(quantity);
                item.setTotalPrice(quantity * product.getDiscountPrice());
                updated.set(true);
            }
            return item;
        }).collect(Collectors.toList());

        // If the product is not already in the cart, add a new cart item
        if (!updated.get()) {
            CartItem cartItem = CartItem.builder()
                    .quantity(quantity)
                    .totalPrice(quantity * product.getDiscountPrice())
                    .cart(cart)
                    .product(product)
                    .build();
            items.add(cartItem);
        }

        cart.setItems(items);
        Cart updatedCart = cartRepository.save(cart);
        return modelMapper.map(updatedCart, CartDto.class);
    }
    @Override
    public CartDto addItemToCart(String userId, AddItemToCartRequest request) {
        int quantity = request.getQuantity();
        String productId = request.getProductId();
        if (quantity <= 0) {
            throw new BadApiRequestException("Requested quantity is not valid !!");
        }

        // Fetch the product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found in database !!"));
        // Fetch the user from the database
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found in database !!"));

        // Fetch the user's cart or create a new one if it doesn't exist
        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setCartId(UUID.randomUUID().toString());
            newCart.setCreatedAt(new Date());
            newCart.setUser(user);
            return newCart;
        });

        // Check if the product is already in the cart and update the quantity
        AtomicReference<Boolean> updated = new AtomicReference<>(false);
        List<CartItem> items = cart.getItems();

        for (CartItem item : items) {
            if (item.getProduct().getProductId().equals(productId)) {
                // Item already present in the cart
                item.setQuantity(quantity);
                item.setTotalPrice(quantity * product.getDiscountPrice());
                updated.set(true);
                break;
            }
        }

        // If the product is not already in the cart, add a new cart item
        if (!updated.get()) {
            CartItem cartItem = CartItem.builder()
                    .quantity(quantity)
                    .totalPrice(quantity * product.getDiscountPrice())
                    .cart(cart)
                    .product(product)
                    .build();
            items.add(cartItem);
        }

        cart.setItems(items);
        Cart updatedCart = cartRepository.save(cart);
        return modelMapper.map(updatedCart, CartDto.class);
    }


    @Override
    public void removeItemFromCart(String userId, int cartItem) {
        CartItem cartItems = cartItemRepository.findById(cartItem).orElseThrow(
                () -> new ResourceNotFoundException("cart item not exist ...")
        );
        cartItemRepository.delete(cartItems);

    }

    @Override
    public void clearCart(String userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("based on id user is not available")
        );
        Cart cart = cartRepository.findByUser(user).orElseThrow(
                () -> new ResourceNotFoundException("based on id user Cart is not available")
        );
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    @Override
    public CartDto getCartByUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("based on id user is not available")
        );
        Cart cart = cartRepository.findByUser(user).orElseThrow(
                () -> new ResourceNotFoundException("based on id user Cart is not available")
        );
        return modelMapper.map(cart, CartDto.class);
    }
}
