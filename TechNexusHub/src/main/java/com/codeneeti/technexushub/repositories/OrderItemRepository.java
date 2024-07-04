package com.codeneeti.technexushub.repositories;

import com.codeneeti.technexushub.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {

}
