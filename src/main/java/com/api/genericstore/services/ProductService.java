package com.api.genericstore.services;

import com.api.genericstore.models.ProductModel;
import com.api.genericstore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductModel save(ProductModel productModel) {
        return productRepository.save(productModel);
    }

    public Page<ProductModel> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public boolean titleAlreadyExists(String title) {
        return productRepository.existsByTitle(title);
    }
}
