package com.api.genericstore.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductOrderDto {

    @NotBlank
    private String title;

    @NotNull
    private Double total_price;

    @NotNull
    private Integer quantity;
}
