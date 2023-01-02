package com.spickandspan.productservice.product.domain.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class ProductResponseDTO {
    private String name;
    private String description;
    private BigDecimal price;
}
