package com.spickandspan.user.product.domain.dto.req;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductRequest {
    private String description;
    private BigDecimal price;
}
