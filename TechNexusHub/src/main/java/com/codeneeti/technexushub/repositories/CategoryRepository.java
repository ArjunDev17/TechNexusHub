package com.codeneeti.technexushub.repositories;

import com.codeneeti.technexushub.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,String> {
}
