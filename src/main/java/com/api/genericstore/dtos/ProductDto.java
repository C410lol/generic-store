package com.api.genericstore.dtos;

import com.api.genericstore.models.StatusModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class ProductDto {

    @NotNull
    private List<String> url_images;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private Integer stock;

    @NotNull
    private StatusModel status;
}
