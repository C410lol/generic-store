package com.api.genericstore.services;

import com.api.genericstore.dtos.ProductOrderDto;
import com.api.genericstore.models.ProductModel;
import com.api.genericstore.models.StatusModel;
import com.api.genericstore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class OrderService {

    private final ProductRepository productRepository;

    public void discountItems(List<ProductOrderDto> productOrderDtoList) {
        for (ProductOrderDto productOrder : productOrderDtoList) {
            ProductModel productModel = productRepository.findByTitle(productOrder.getTitle());
            int finalStock = productModel.getStock() - productOrder.getQuantity();
            productModel.setStock(finalStock);
            productRepository.save(productModel);
        }
    }

    public boolean isStatusAndStockValid(List<ProductOrderDto> productOrderDtoList) {
        for (ProductOrderDto productOrder : productOrderDtoList) {
            ProductModel productModel = productRepository.findByTitle(productOrder.getTitle());
            int finalStock = productModel.getStock() - productOrder.getQuantity();
            if (productModel.getStatus() == StatusModel.UNAVAILABLE) {
                return false;
            }
            if (finalStock < 0) {
                return false;
            }
        }
        return true;
    }
}
