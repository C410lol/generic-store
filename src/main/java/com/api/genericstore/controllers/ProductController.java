package com.api.genericstore.controllers;

import com.api.genericstore.dtos.ProductDto;
import com.api.genericstore.models.ProductModel;
import com.api.genericstore.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@RequiredArgsConstructor

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final LocalDateTime localDateTime;

    @PostMapping("/create")
    public ResponseEntity<Object> createProduct(@RequestBody @Valid ProductDto productDto) {
        if (productService.titleAlreadyExists(productDto.getTitle())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Some product already have this name!");
        }
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productDto, productModel);
        productModel.setRegistration_date(localDateTime);
        productModel.setLast_update(localDateTime);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productModel));
    }

    @GetMapping
    public ResponseEntity<Object> listAllProducts(@PageableDefault Pageable pageable) {
        Page<ProductModel> allProducts = productService.findAllProducts(pageable);
        if (allProducts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("No product found :(");
        }
        return ResponseEntity.status(HttpStatus.OK).body(allProducts);
    }
}
