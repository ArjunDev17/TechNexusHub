package com.codeneeti.technexushub.repositories;

import com.codeneeti.technexushub.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
}
