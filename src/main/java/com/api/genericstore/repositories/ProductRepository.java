package com.api.genericstore.repositories;

import com.api.genericstore.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductModel, UUID> {

    ProductModel findByTitle(String title);
    boolean existsByTitle(String title);
}
