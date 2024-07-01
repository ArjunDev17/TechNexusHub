package com.codeneeti.technexushub.repositories;

import com.codeneeti.technexushub.entities.Category;
import com.codeneeti.technexushub.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {

    Page<Product> findByTitleContaining(String subTitle,Pageable pageable);
    Page<Product> findByLiveTrue(Pageable pageable);
    //other
    Page<Product>findByCategory(Category category,Pageable pageable);
}
