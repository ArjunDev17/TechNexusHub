package com.codeneeti.technexushub.repositories;

import com.codeneeti.technexushub.entities.Cart;
import com.codeneeti.technexushub.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,String> {
    Optional<Cart> findByUser(User user);
//    Cart findByUser(User user);
}
