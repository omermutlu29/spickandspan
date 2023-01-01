package com.spickandspan.user.product.domain.dto.res;

import com.spickandspan.user.user.domain.dto.res.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class ProductResponseDTO {
    private String name;
    private String description;
    private BigDecimal price;
}
