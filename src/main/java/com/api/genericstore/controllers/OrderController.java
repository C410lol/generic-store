package com.api.genericstore.controllers;

import com.api.genericstore.dtos.ProductOrderDto;
import com.api.genericstore.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/purchase")
    public ResponseEntity<String> purchaseTime(@RequestBody @Valid List<ProductOrderDto> productOrderDtoList) {
        if (!productOrderDtoList.isEmpty()) {
            if (orderService.isStatusAndStockValid(productOrderDtoList)) {
                orderService.discountItems(productOrderDtoList);
                return ResponseEntity.status(HttpStatus.OK).body("Successful purchase ^_^");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Product unavailable or insufficient stock:(");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No items in cart '_'");
    }
}
