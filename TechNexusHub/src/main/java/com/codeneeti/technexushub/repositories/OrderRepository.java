package com.codeneeti.technexushub.repositories;

import com.codeneeti.technexushub.entities.Order;
import com.codeneeti.technexushub.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByUser(User user);
}
